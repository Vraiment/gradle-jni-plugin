package com.vraiment.gradle.jni

import org.gradle.api.tasks.AbstractExecTask

class GenerateJniTask extends AbstractExecTask {
    private String classpath

    private String outputDir

    private String className

    GenerateJniTask() {
        super(GenerateJniTask)

        executable 'javah'
        args = generateArguments()
    }

    void setClasspath(final String classpath) {
        this.classpath = classpath
        args = generateArguments()
    }

    void setOutputDir(final String outputDir) {
        this.outputDir = outputDir
        args = generateArguments()
    }

    void setClassName(final String className) {
        this.className = className
        args = generateArguments()
    }

    private List<String> generateArguments() {
        return [ '-cp', classpath, '-d', outputDir, className ]
    }
}
