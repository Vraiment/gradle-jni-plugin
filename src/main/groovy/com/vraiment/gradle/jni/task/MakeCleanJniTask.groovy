package com.vraiment.gradle.jni.task

import org.gradle.api.tasks.TaskAction

class MakeCleanJniTask extends AbstractMakeTask {
    MakeCleanJniTask() {
        super(MakeCleanJniTask)
    }

    @TaskAction
    protected void exec() {
        super.execMake('clean')
    }
}
