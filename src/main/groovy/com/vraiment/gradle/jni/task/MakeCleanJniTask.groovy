package com.vraiment.gradle.jni.task

import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

/**
 * Task to execute "clean" on a Makefile.
 */
class MakeCleanJniTask extends AbstractMakeTask {
    private static final Logger logger = Logging.getLogger(MakeCleanJniTask)

    /**
     * Constructs a new instance of MakeCleanJniTask.
     */
    MakeCleanJniTask() {
        super(MakeCleanJniTask)
    }

    @TaskAction
    protected void exec() {
        logger.info("Executing make clean")

        super.execMake('clean')
    }
}
