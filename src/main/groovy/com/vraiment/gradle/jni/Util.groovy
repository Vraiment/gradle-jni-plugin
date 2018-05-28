package com.vraiment.gradle.jni

import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

class Util {
    private static final Logger logger = Logging.getLogger(Util)

    /**
     * Validates the given file object is not null, and that if exists is a
     * directory. If it doesn't exist it attempts to create the directory.
     *
     * @param dirObject The object to validate.
     * @param name The name of the object (for logging proposes).
     */
    static void validateAndCreateDir(File dirObject, String name) {
        assert dirObject : "$name should contain a value"

        if (dirObject.exists()) {
            assert dirObject.directory : "Value of $name should be a directory"
        } else {
            logger.info("Creating $name")
            dirObject.mkdirs()
        }
    }
}
