package com.vraiment.gradle.jni.task

import org.gradle.api.tasks.TaskAction

class CleanJniTask extends AbstractMakeTask {
    CleanJniTask() {
        super(CleanJniTask)
    }

    @TaskAction
    protected void exec() {
        super.execMake('clean')
    }
}
