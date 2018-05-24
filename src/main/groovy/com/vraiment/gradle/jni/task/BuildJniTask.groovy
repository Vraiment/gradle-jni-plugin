package com.vraiment.gradle.jni.task

import org.gradle.api.tasks.AbstractExecTask

class BuildJniTask extends AbstractExecTask {
    private String sourcesDir

    BuildJniTask() {
        super(BuildJniTask)

        executable 'make'
        args = generateArguments()
    }

    void setSourcesDir(final String sourcesDir) {
        this.sourcesDir = sourcesDir
        args = generateArguments()
    }

    void setJvmHome(final String jvmHome) {
        environment 'JVM_HOME', jvmHome
    }

    void setOutputDir(final String outputDir) {
        environment 'OUTPUT', outputDir
    }

    private List<String> generateArguments() {
        return [ '-C', sourcesDir ]
    }
}
