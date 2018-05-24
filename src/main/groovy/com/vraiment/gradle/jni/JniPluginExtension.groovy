package com.vraiment.gradle.jni

import org.gradle.api.Project

class JniPluginExtension {
    List<String> classes
    String headersDir

    JniPluginExtension(Project project) {
        // These should initialize to useful values
        classes = []
        headersDir = ''
    }
}
