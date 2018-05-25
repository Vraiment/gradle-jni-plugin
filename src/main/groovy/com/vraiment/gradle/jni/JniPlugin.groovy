package com.vraiment.gradle.jni

import org.gradle.api.Project
import org.gradle.api.Plugin

import com.vraiment.gradle.jni.task.GenerateJniTask
import com.vraiment.gradle.jni.task.MakeCleanJniTask
import com.vraiment.gradle.jni.task.MakeJniTask

class JniPlugin implements Plugin<Project> {
    void apply(Project project) {
        def extension = project.extensions.create('jni', JniPluginExtension, project)

        project.tasks.create('generateJni', GenerateJniTask) { }

        project.tasks.create('makeJni', MakeJniTask) { }

        project.tasks.create('makeCleanJni', MakeCleanJniTask) { }
    }
}
