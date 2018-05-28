package com.vraiment.gradle.jni.task

import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.AbstractExecTask

import static com.vraiment.gradle.jni.Util.validateAndCreateDir

/**
 * Abstract class to execute a Makefile.
 */
class AbstractMakeTask extends AbstractExecTask {
    private static final Logger logger = Logging.getLogger(AbstractMakeTask)

    /**
     * The directory that contains the Makefile.
     */
    File makeFileDir

    /**
     * The path where the Makefile should output the build artifacts.
     */
    File outputDir

    /**
     * Constructs a new instance of the AbstractMakeTask class.
     */
    AbstractMakeTask(Class taskType) {
        super(taskType)

        executable = 'make'
    }

    /**
     * Executes the makefile in "makeFileDir" with the given arguments.
     */
    protected void execMake(String... arguments) {
        assert makeFileDir?.directory : 'makeFileDir should point to a directory'
        validateAndCreateDir(outputDir, 'outputDir')

        environment 'OUTPUT', outputDir.path

        args = [ '-C', makeFileDir ] + arguments.toList()

        logger.info("Executing Makefile in $makeFileDir with output as $outputDir")

        super.exec()
    }
}
