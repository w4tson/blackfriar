package com.blackfriar.gradle;

import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.interpret.RenderResult;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.filters.BaseFilterReader;
import org.apache.tools.ant.filters.ChainableReader;
import org.apache.tools.ant.types.PropertySet;
import org.gradle.api.file.FileCollection;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class ExpandJ2Properties extends BaseFilterReader implements ChainableReader {
    private static final int EOF = -1;
    private char[] buffer;
    private int index;
    private PropertySet propertySet;
    private FileCollection bindingsFiles;
    private Jinjava jj = new Jinjava();
    private Map<String, Object> extraBindings = new HashMap<>();


    public ExpandJ2Properties() {
    }

    public ExpandJ2Properties(Reader r) {
        super(r);
    }

    public void add(PropertySet propertySet) {
        if (this.propertySet != null) {
            throw new BuildException("expand properties filter accepts only propertyset");
        } else {
            this.propertySet = propertySet;
        }
    }

    public FileCollection getBindingsFiles() {
        return bindingsFiles;
    }

    public void setBindingsFiles(FileCollection bindingsFiles) {
        this.bindingsFiles = bindingsFiles;
    }

    public Map<String, Object> getExtraBindings() {
        return extraBindings;
    }

    public void setExtraBindings(Map<String, Object> extraBindings) {
        this.extraBindings = extraBindings;
    }

    public int read() throws IOException {
        if (this.index > -1) {
            if (this.buffer == null) {
                String data = this.readFully();

                Map<String, Object> bindings = new HashMap<>();
                for (File bindingsFile : bindingsFiles) {
                    String bindingsText = new String(Files.readAllBytes(bindingsFile.toPath()));
                    String bindingsTextExpanded = jj.render(bindingsText, bindings);

                    Map<String, Object> tempBindings = (Map<String, Object>) new Yaml().load(bindingsTextExpanded);
                    for (Map.Entry<String, Object> tempBinding : tempBindings.entrySet()) {
                        bindings.putIfAbsent(tempBinding.getKey(), tempBinding.getValue());
                    }
                }

                bindings.putAll(extraBindings);

                RenderResult renderResult = jj.renderForResult(data, bindings);
                String expanded = renderResult.getOutput();

                if (renderResult.getErrors().size() > 0) {
                    System.out.println("Jin Java Errrors "+ renderResult.getErrors());
                }

                this.buffer = expanded == null ? new char[0] : expanded.toString().toCharArray();
            }

            if (this.index < this.buffer.length) {
                return this.buffer[this.index++];
            }

            this.index = -1;
        }

        return -1;
    }

    @Override
    public Reader chain(Reader reader) {
        ExpandJ2Properties newFilter = new ExpandJ2Properties(reader);
        newFilter.setProject(this.getProject());
        newFilter.add(this.propertySet);
        return newFilter;
    }
}
