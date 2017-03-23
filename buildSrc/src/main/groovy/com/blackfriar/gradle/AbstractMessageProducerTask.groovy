package com.blackfriar.gradle

import groovy.text.SimpleTemplateEngine
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.yaml.snakeyaml.Yaml

class AbstractMessageProducerTask extends DefaultTask {

    def appRootDir = "${project.getRootDir()}"

    @Input
    def keystore = "$appRootDir/src/main/deploy/ansible/roles/blackfriar/files/ssl/blackfriar.pfx"

    @Input
    def keystorePasword = "mypass"

    @Input
    def truststore = "$appRootDir/src/main/deploy/ansible/roles/blackfriar/files/ssl/truststore.jks"

    @Input
    def truststorePassword = "mypass"

    @Input
    def exchangeName = ""

    @Input
    def exchangeType = "direct"

    @InputFile
    File messageFile

    @Input
    def env = 'dev'

    def engine = new SimpleTemplateEngine()

    AmqpProducer amqpProducer() {
        // parse out the queue location from the group_vars environment
        File groupVarsFile = new File("$appRootDir/blackfriar-web/src/main/deploy/ansible/group_vars/$env")
        def groupVarsText = groupVarsFile.text
        def groupVars = new Yaml().load(groupVarsText)
        String rabbitHost = groupVars.rabbitmq_host
        int port = 5672

        new AmqpProducer(keystore,
                keystorePasword,
                truststore,
                truststorePassword,
                rabbitHost,
                port,
                getExchangeName(),
                getExchangeType())

    }
}
