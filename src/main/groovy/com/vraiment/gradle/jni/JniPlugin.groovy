package com.vraiment.gradle.jni

import org.gradle.api.Project
import org.gradle.api.Plugin

import com.vraiment.gradle.jni.task.BuildJniTask
import com.vraiment.gradle.jni.task.CleanJniTask
import com.vraiment.gradle.jni.task.GenerateJniTask

class JniPlugin implements Plugin<Project> {
    void apply(Project project) {
        def extension = project.extensions.create('jni', JniPluginExtension, project)

        project.tasks.create('generateJni', GenerateJniTask) { }

        project.tasks.create('buildJni', BuildJniTask) { }

        project.tasks.create('cleanJni', CleanJniTask) { }
    }
}
