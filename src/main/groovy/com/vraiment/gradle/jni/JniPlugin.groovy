package com.vraiment.gradle.jni

import org.gradle.api.Project
import org.gradle.api.Plugin

import com.vraiment.gradle.jni.task.GenerateJniTask
import com.vraiment.gradle.jni.task.MakeJniTask

import static com.vraiment.gradle.jni.Util.GENERATE_JNI
import static com.vraiment.gradle.jni.Util.MAKE_JNI

class JniPlugin implements Plugin<Project> {
    void apply(Project project) {
        def extension = project.extensions.create('jni', JniPluginExtension, project)

        configureGenerateJniTask(project, extension)
        configureMakeJniTask(project, extension)
    }

    private void configureGenerateJniTask(Project project, JniPluginExtension extension) {
        def task = project.tasks.create(GENERATE_JNI, GenerateJniTask) {
            doFirst {
                if (!generatedHeadersDir) {
                    generatedHeadersDir = extension.generatedHeadersDir
                }

                if (!jdk) {
                    jdk = extension.jdk
                }

                if (!classpath) {
                    classpath = extension.classpath
                }

                if (!classes) {
                    classes = extension.classes
                }
            }
        }

        project.tasks.getByName('assemble').dependsOn GENERATE_JNI
        task.dependsOn 'compileJava'
    }

    private void configureMakeJniTask(Project project, JniPluginExtension extension) {
        def task = project.tasks.create(MAKE_JNI, MakeJniTask) {
            doFirst {
                if (!makeFileDir) {
                    makeFileDir = extension.makeFileDir
                }

                if (!makeOutputDir) {
                    makeOutputDir = extension.makeOutputDir
                }

                if (!jdk) {
                    jdk = extension.jdk
                }
            }
        }

        task.dependsOn GENERATE_JNI
    }
}
