package com.vraiment.gradle.jni.task

import org.gradle.api.tasks.AbstractExecTask

class AbstractMakeTask extends AbstractExecTask {
    String sourcesDir

    String outputDir

    AbstractMakeTask(Class taskType) {
        super(taskType)

        executable = 'make'
    }

    protected void execMake(String... arguments) {
        assert sourcesDir != null
        assert outputDir != null

        environment 'OUTPUT', outputDir.replace(' ', '\\ ')

        args = [ '-C', sourcesDir ] + arguments.toList()

        super.exec()
    }
}
