[![Build Status](https://api.shippable.com/projects/54eda7bc5ab6cc13528dbc5c/badge?branchName=master)](https://app.shippable.com/projects/54eda7bc5ab6cc13528dbc5c/builds/latest)

[![Download](https://api.bintray.com/packages/boxhead/gradle-plugins/gradle-gitignore-plugin/images/download.svg) ](https://bintray.com/boxhead/gradle-plugins/gradle-gitignore-plugin/_latestVersion)


# Gradle Gitignore Plugin
Simple plugin that provides a task and configuration to write `.gitignore` file.

# Why
This does seem on the face of it a stupid plugin since if you have created a project and gone to the trouble to create the configuration for the gitignore plugin you could have just written the .gitignore yourself. This plugin is really designed to work with other plugins that are used to generate templates and preconfigured projects that are 'opinionated' about how a project should be set up. This plugin is ideally suited to be used in conjunction with the gradle-pater-build-plugin that allows you to define your opinions on how your project should be set up.

# Simple Usage
See below for an example build script that defines the ignore file contents.

```
buildscript {
	repositories {
		jcenter()
	}
	dependencies {
	    //The gitignore plugin.
		classpath 'com.fizzpod:gradle-gitignore-plugin:1.0.0'
	}
}

apply plugin: 'com.fizzpod.gitignore'

//section for defining your ignore patterns.
gitignore {
    ignore 'bin'
    ignore 'build'
    ignore 'another'
}

```

# Tasks
The plugin defines two tasks: `displayGitignore` and `writeGitignore`. If you run gradle tasks then you will see these two grouped under a Gitignore section.

## writeGitignore
Writes the values from the gitignore configuration to the `.gitignore` file. Note that this overwrites it does not append or merge the contents.

## displayGitignore
Displays the contents of the `.gitignore` file if it exists.
