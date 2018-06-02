package com.vraiment.gradle.jni

import org.gradle.api.Project
import org.gradle.api.file.FileCollection
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.tasks.SourceSet

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
    File makeFileDir

    /**
     * The output directory for native artifacts.
     */
    File makeOutputDir

    /**
     * The directory that contains the JDK.
     */
    File jdk

    /**
     * The classpath that will be used for generating the header files and executing the Makefile.
     */
    FileCollection classpath

    /**
     * Initialize this extension object with the given project.
     */
    JniPluginExtension(Project project) {
        classes = []
        generatedHeadersDir = new File("${project.buildDir}/jni/headers")
        makeFileDir = new File("${project.projectDir}/src/main/jni")
        makeOutputDir = new File("${project.buildDir}/jni/make")
        jdk = extractJdkDir(project)
        classpath = extractClasspath(project)
    }

    private String extractJdkDir(project) {
        // TODO
        return null
    }

    private FileCollection extractClasspath(Project project) {
        def convention = project.convention.getPlugin(JavaPluginConvention)
        def outputSourceSet = convention.sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME).output

        return outputSourceSet.classesDirs
    }
}
