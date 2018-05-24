package com.vraiment.gradle.jni

import org.gradle.api.tasks.AbstractExecTask

class CleanJniTask extends AbstractExecTask {
    private String sourcesDir

    CleanJniTask() {
        super(CleanJniTask)

        executable 'make'
        args = generateArguments()
    }

    void setSourcesDir(final String sourcesDir) {
        this.sourcesDir = sourcesDir
        args = generateArguments()
    }

    void setOutputDir(final String outputDir) {
        environment 'OUTPUT', outputDir
    }

    private List<String> generateArguments() {
        return [ '-C', sourcesDir, 'clean' ]
    }
}
