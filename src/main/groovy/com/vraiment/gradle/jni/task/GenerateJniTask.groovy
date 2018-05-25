package com.vraiment.gradle.jni.task

import org.gradle.api.Project
import org.gradle.api.file.FileCollection
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.AbstractExecTask
import org.gradle.api.tasks.TaskAction

/**
 * Task that will generate the headers from the given classes.
 */
class GenerateJniTask extends AbstractExecTask {
    private static final Logger logger = Logging.getLogger(GenerateJniTask)

    /**
     * The directory that contains the $JVM_HOME value, if not set it will just
     * call 'javah' instead of using the full path.
     */
    File jvmHome

    /**
     * The classpath that will be used to search for the classes containing the
     * native methods.
     */
    FileCollection classpath

    /**
     * The directory where the generated headers will go.
     */
    File generatedHeadersDir

    /**
     * The list of classes (with fully qualified names) that contain the native
     * methods.
     */
    List<String> classes = []

    /**
     * Constructs a new instance of the GenerateJniTask class.
     */
    GenerateJniTask() {
        super(GenerateJniTask)
    }

    @TaskAction
    protected void exec() {
        validateProperties()

        executable buildExecutable()
        args = buildArguments()

        super.exec()
    }

    private void validateProperties() {
        logger.info("jvmHome => ${jvmHome}")
        logger.info("classpath => ${classpath.asPath}")
        logger.info("generatedHeadersDir => ${generatedHeadersDir}")
        logger.info("classes => ${classes}")

        if (jvmHome) {
            assert jvmHome.exists() : 'The JVM home value doesn\'t exist'
            assert jvmHome.directory : 'The JVM home value is not a directory'
        }

        assert generatedHeadersDir : 'The generated headers path is required'
        if (generatedHeadersDir.exists()) {
            assert generatedHeadersDir.directory : 'The generated headers path is not a directory'
        } else {
            log.info('Creating generated headers dir')
            generatedHeadersDir.mkdirs()
        }

        assert classes : 'The list of classes to search for native methods is empty'
    }

    private String buildExecutable() {
        if (jvmHome) {
            return [jvmHome.path, 'bin', 'javah'].join(File.separator)
        } else {
            log.info('Using default search path for javah')
            return 'javah'
        }
    }

    private List<String> buildArguments() {
        return [ '-cp', classpath.asPath, '-d', generatedHeadersDir.path ] + classes
    }
}
