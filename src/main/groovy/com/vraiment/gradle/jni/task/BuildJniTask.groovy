package com.vraiment.gradle.jni.task

import org.gradle.api.tasks.TaskAction

class BuildJniTask extends AbstractMakeTask {
    String jvmHome

    BuildJniTask() {
        super(BuildJniTask)
    }

    @TaskAction
    protected void exec() {
        assert jvmHome != null

        environment 'JVM_HOME', jvmHome

        super.execMake()
    }
}
