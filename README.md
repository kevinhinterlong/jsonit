# jsonit

`jsonit` is a simple example of how to use [`Antlr`](https://www.antlr.org/) to define a grammar and use it in a Java (and gradle) based project


The project uses the [`JSON.g4`](https://github.com/antlr/grammars-v4/blob/master/json/JSON.g4) grammar from the  [antlr/grammars-v4](https://github.com/antlr/grammars-v4) repo

> NOTE: This project is _not_ meant to be a useful json parser.



## Antlr setup with gradle

The following lines from [`build.gradle`](build.gradle) define how to set up the `antlr` plugin which will generate the Java sources for you Parser/Lexer

```groovy
// First we must pull in the antlr plugin
apply plugin: 'antlr'

// then decide where to store the generated java code
def generatedSources = [
    rootDir: 'generated-src/',
    javaDir: 'generated-src/java'
]

// then add the generated sources to our project
sourceSets {
    main {
        java {
            srcDir generatedSources.javaDir
        }
    }
}

dependencies {
    // pull in 'org.antlr:antlr4:4.7' for the `antlr` configuration
    // (this is just for building the sources files)
    antlr 'org.antlr:antlr4:4.7'
    // add the antlr runtime so we can use the generated sources
    implementation 'org.antlr:antlr4-runtime:4.7'
}

// configure the grammar generation
// more info: https://docs.gradle.org/current/userguide/antlr_plugin.html
generateGrammarSource {
    arguments += ['-visitor', '-long-messages']
    outputDirectory = file(generatedSources.javaDir)
}
// ensures the grammar source files are generated before building the project
compileJava.dependsOn generateGrammarSource

// delete the generated sources as part of the 'clean' step
clean {
    delete generatedSources.rootDir
}
```



## Working with IntelliJ

IntelliJ by default will use it's own configuration for running Gradle project which is great for most cases; however it does not work with complex configurations.

If you try to run the project with IntelliJ it will fail because it does not run this step

```groovy
compileJava.dependsOn generateGrammarSource
```

To solve this you can set

**Build, Execution, Deployment** → **Build Tools** → **Gradle** → **Runner** → `Delegate IDE build/run actions to gradle` to true

> NOTE: You could also manually generate the grammar once (or whenever you change it)
> ```bash
> $ ./gradlew generateGrammarSource 
> ```



## Build

To build the project, clone it then run

```bash
$ ./gradlew build
```


