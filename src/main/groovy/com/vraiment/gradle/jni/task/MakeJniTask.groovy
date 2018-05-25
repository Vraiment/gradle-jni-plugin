package com.vraiment.gradle.jni.task

import org.gradle.api.tasks.TaskAction

class MakeJniTask extends AbstractMakeTask {
    String jvmHome

    MakeJniTask() {
        super(MakeJniTask)
    }

    @TaskAction
    protected void exec() {
        assert jvmHome != null

        environment 'JVM_HOME', jvmHome

        super.execMake()
    }
}
