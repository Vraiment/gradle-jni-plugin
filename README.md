# Gradle JNI plugin
-------------------

This is a quick and dirty implementation of a plugin to generate and compile JNI code with Gradle using a Makefile for the native portion.

## Tasks

It consists on three different tasks: *generateJni*, *makeJni* and *makeCleanJni*.

### Task *generateJni*

This task is in charge of generating the header files out of Java classes. It requires several values in order to work correctly:
- *jdk*: This is a file object which should point to the JDK that contains the *javah* executable.
- *classpath*: The classpath that will be used to search for classes with native methods.
- *generatedHeadersDir*: This is the output dir for the headers that will be generated.
- *classes*: A list of string, which contain the fully qualified names of all the classes to be searched for native methods.

One example of configuring the task *generateJni*:

```groovy
generateJni {
    jdk = file('/Library/Java/JavaVirtualMachines/jdk1.8.0_31.jdk/Contents/Home')
    classpath = files("$buildDir/classes")
    generatedHeadersDir = file("$src/jni")
    classes = [ 'org.myproject.native.Class1', 'org.myproject.native.Class2' ]
}
```

### Task *makeJni*

This task is in charge of executing the given make file in order to build the library with the given Makefile. It requires the following values to work correctly:
- *generatedHeadersDir*: Contains a path where the Makefile should search for the JNI generated, this value is available to the Makefile via the `GENERATED_HEADERS_DIR` variable.
- *makeFileDir*: Contains the path where the Makefile to be executed is located.
- *makeOutputDir*: Contains the path where the Makefile should place generated artifacts, this value is available to the Makefile via the `OUTPUT_DIR` variable.
- *jdk*: Contains the path where the JDK is located, this value is available to the Makefile via the `JDK_DIR` variable.

One example of configuring the task *makeJni*:

```groovy
makeJni {
    makeFileDir = file("$src/jni")
    makeOutputDir = file("$buildDir/jni")
    jdk = file('/Library/Java/JavaVirtualMachines/jdk1.8.0_31.jdk/Contents/Home')
}
```

Additionally the *makeJni* task have two additional options for further configuring the Makefile:

- *arguments*: A property which is a list of strings that will be passed to the makefile, you cannot override the `-C` argument.
- *environment*: A method to configure the environment for the makefile. As first argument takes the name of the variable and as second argument the value of the variable.

## Configuring all the tasks

The pluging makes a `jni` extension available in order to set all the values necessary for the different tasks instead of configuring each task separately. Example:

```groovy
jni.classes = [ 'org.myproject.native.Class1', 'org.myproject.native.Class2' ]
jni.jdk = file('/Library/Java/JavaVirtualMachines/jdk1.8.0_31.jdk/Contents/Home')
jni.generatedHeadersDir = file("$buildDir/jni")
...
```
