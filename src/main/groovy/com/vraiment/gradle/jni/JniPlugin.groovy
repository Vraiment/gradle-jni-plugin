package com.vraiment.gradle.jni

import org.gradle.api.Project
import org.gradle.api.Plugin

import com.vraiment.gradle.jni.task.GenerateJniTask
import com.vraiment.gradle.jni.task.MakeJniTask

import static org.gradle.api.plugins.BasePlugin.ASSEMBLE_TASK_NAME;
import static org.gradle.api.plugins.JavaPlugin.COMPILE_JAVA_TASK_NAME

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

        addToAssemble(project, GENERATE_JNI)
        task.dependsOn COMPILE_JAVA_TASK_NAME
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

        addToAssemble(project, MAKE_JNI)
        task.dependsOn GENERATE_JNI
    }

    private static void addToAssemble(Project project, String taskName) {
        project.tasks.getByName(ASSEMBLE_TASK_NAME).dependsOn taskName
    }
}
