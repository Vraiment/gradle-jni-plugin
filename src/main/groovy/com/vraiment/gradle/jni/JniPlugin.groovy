package com.vraiment.gradle.jni

import org.gradle.api.Project
import org.gradle.api.Plugin

class JniPlugin implements Plugin<Project> {
    void apply(Project project) {
        def extension = project.extensions.create('jni', JniPluginExtension, project)
    }
}
