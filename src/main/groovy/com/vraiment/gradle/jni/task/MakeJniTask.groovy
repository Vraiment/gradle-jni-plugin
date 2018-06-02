package com.vraiment.gradle.jni.task

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction
import org.gradle.process.internal.ExecActionFactory

import javax.inject.Inject

/**
 * Task to execute a Makefile.
 */
class MakeJniTask extends DefaultTask {
    private static def logger = Logging.getLogger(MakeJniTask)

    private static JDK_ENV_DIR_VAR = 'JDK_DIR'

    private static OUTPUT_DIR_ENV_VAR = 'OUTPUT_DIR'

    private static GENERATED_HEADERS_DIR_ENV_DAR = 'GENERATED_HEADERS_DIR'

    private def execAction = this.getExecActionFactory().newExecAction()

    /**
     * THe directory where the generated headers are placed.
     */
    File generatedHeadersDir

    /**
     * The directory that contains the makefile.
     */
    File makeFileDir

    /**
     * The directory where the makefile should put the build artifacts.
     */
    File makeOutputDir

    /**
     * The directory where the JDK is stored.
     */
    File jdk

    /**
     * Additional arguments for the makefile.
     */
    List<String> arguments

    /**
     * Sets the environment variable with the given name to the given value for the Makefile.
     *
     * The variable should not be either JDK_DIR or OUTPUT_DIR or GENERATED_HEADERS_DIR.
     *
     * @param name The name of the environment variable.
     * @param value The value of the environment variable.
     *
     * @return This same instance of a MakeJniTask.
     */
    def environment(String name, Object value) {
        checkArgument(JDK_ENV_DIR_VAR, name)
        checkArgument(OUTPUT_DIR_ENV_VAR, name)
        checkArgument(GENERATED_HEADERS_DIR_ENV_DAR, name)

        execAction.environment name, value

        return this
    }

    @TaskAction
    def make() {
        logger.info("JDK => ${jdk.path}")
        logger.info("output dir => ${makeOutputDir.path}")
        logger.info("headers dir => ${generatedHeadersDir}")
        logger.info("makefile dir => ${makeFileDir.path}")
        logger.info("arguments => $arguments")

        environment JDK_ENV_DIR_VAR, jdk.path
        environment OUTPUT_DIR_ENV_VAR, makeOutputDir.path
        environment GENERATED_HEADERS_DIR_ENV_DAR,generatedHeadersDir.path

        execAction.args = [ '-C', makeFileDir.path ] + buildArguments()

        execAction.execute()
    }

    @Inject
    protected ExecActionFactory getExecActionFactory() {
        throw new UnsupportedOperationException()
    }

    private buildArguments() {
        if (arguments == null) {
            return null
        }

        if (arguments.contains('-C')) {
            throw new RuntimeException("Trying to override the Makefile location")
        }

        return arguments
    }

    private static checkArgument(String constant, String value) {
        if (constant == value) {
            throw IllegalArgumentException(constant)
        }
    }
}
