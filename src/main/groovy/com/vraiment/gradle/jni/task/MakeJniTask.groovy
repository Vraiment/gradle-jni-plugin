package com.vraiment.gradle.jni.task

import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

import static com.vraiment.gradle.jni.Util.JNI

/**
 * Task to execute a Makefile with no arguments but setting the path to the JDK.
 */
class MakeJniTask extends AbstractMakeTask {
    private static final Logger logger = Logging.getLogger(MakeJniTask)

    /**
     * The directory that contains the JDK.
     */
    File jdk

    /**
     * Constructs a new instance of MakeJniTask.
     */
    MakeJniTask() {
        super(MakeJniTask)

        description = 'Executes the Makefile to compile JNI sources.'
        group = JNI
    }

    @TaskAction
    protected void exec() {
        assert jdk?.directory : 'jdk should point to a directory'

        environment 'JDK', jdk.path

        logger.info("Executing makefile with JDK in ${jdk.path}")

        super.execMake()
    }
}
