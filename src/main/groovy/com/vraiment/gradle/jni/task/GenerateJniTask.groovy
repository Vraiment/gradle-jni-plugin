package com.vraiment.gradle.jni.task

import org.gradle.api.file.FileCollection
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.AbstractExecTask
import org.gradle.api.tasks.TaskAction

import static com.vraiment.gradle.jni.Util.JNI
import static com.vraiment.gradle.jni.Util.validateAndCreateDir

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

        description = 'Generates JNI header files.'
        group = JNI
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
        logger.info("classpath => ${classpath?.asPath}")
        logger.info("generatedHeadersDir => ${generatedHeadersDir}")
        logger.info("classes => ${classes}")

        assert jvmHome?.directory : 'jvmHome should point to a directory'
        assert classpath : 'Classpath should be set'

        validateAndCreateDir(generatedHeadersDir, 'generatedHeadersDir')

        assert classes : 'The list of classes to search for native methods is empty'
    }

    private String buildExecutable() {
        if (jvmHome) {
            return [jvmHome.path, 'bin', 'javah'].join(File.separator)
        } else {
            logger.info('Using default search path for javah')
            return 'javah'
        }
    }

    private List<String> buildArguments() {
        return [ '-cp', classpath.asPath, '-d', generatedHeadersDir.path ] + classes
    }
}
