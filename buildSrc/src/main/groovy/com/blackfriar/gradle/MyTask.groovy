package com.blackfriar.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class MyTask extends DefaultTask {

    @TaskAction
    def action() {
        println "Hello, world!"
    }
}
