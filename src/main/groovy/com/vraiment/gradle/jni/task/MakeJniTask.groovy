package com.vraiment.gradle.jni.task

import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

/**
 * Task to execute a Makefile with no arguments but setting the JVM home.
 */
class MakeJniTask extends AbstractMakeTask {
    private static final Logger logger = Logging.getLogger(MakeJniTask)

    /**
     * The directory that contains the JVM home.
     */
    File jvmHome

    /**
     * Constructs a new instance of MakeJniTask.
     */
    MakeJniTask() {
        super(MakeJniTask)
    }

    @TaskAction
    protected void exec() {
        assert jvmHome?.directory : 'jvmHome should point to a directory'

        environment 'JVM_HOME', jvmHome.path

        logger.info("Executing makefile with JVM home in ${jvmHome.path}")

        super.execMake()
    }
}
