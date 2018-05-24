package com.vraiment.gradle.jni

import org.gradle.api.tasks.AbstractExecTask
import org.gradle.api.tasks.TaskAction

class GenerateJniTask extends AbstractExecTask {
    String classpath

    String outputDir

    List<String> classes = []

    GenerateJniTask() {
        super(GenerateJniTask)

        executable 'javah'
    }

    @TaskAction
    protected void exec() {
        assert classpath != null
        assert outputDir != null
        assert classes

        args = [ '-cp', classpath, '-d', outputDir ] + classes

        super.exec()
    }
}
