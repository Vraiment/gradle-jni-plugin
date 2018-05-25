package com.vraiment.gradle.jni

import org.gradle.api.Project
import org.gradle.api.file.FileCollection

/**
 * Extension which contains general values for the plugin.
 */
class JniPluginExtension {
    /**
     * The list of classes that contain native methods.
     */
    List<String> classes

    /**
     * The output directory that will contain the generated sources dir.
     */
    File generatedHeadersDir

    /**
     * The input directory that contains the Makefile for the native implementation.
     */
    File makeSourcesDir

    /**
     * The output directory for native artifacts.
     */
    File makeOutputDir

    /**
     * The directory that contains the $JVM_HOME value.
     */
    File jvmHome

    /**
     * The classpath that will be used for generating the header files and executing the Makefile.
     */
    FileCollection classPath

    /**
     * Initialize this extension object with the given project.
     */
    JniPluginExtension(Project project) {
        classes = []
        generatedHeadersDir = new File("${project.projectDir}/src/jni")
        makeSourcesDir = generatedHeadersDir
        makeOutputDir = new File("${project.buildDir}/jni")
        jvmHome = extractJvmHome(project)
        classPath = extractClassPath(project)
    }

    private String extractJvmHome(project) {
        // TODO
        return null
    }

    private String extractClassPath(project) {
        // TODO
        return null
    }
}
