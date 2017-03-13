package com.blackfriar.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction

class Jinja2Task extends DefaultTask {

    @InputFiles
    def bindingFiles;

    @Input
    def from

    @Input
    def rename = { String n -> n.replace('.js', '') }

    @Input
    def into

    @TaskAction
    def action() {
        project.copy {
            from from
            into into
            rename this.rename
            filter(ExpandJ2Properties, bindingFiles: this.bindingFiles, extraBindings: ['blackfriar_version': project.version])
        }
    }

    public void setRename(Closure cl){
        this.rename = cl;
    }

    public void setRename(String r) {
        this.rename = r;
    }
}
