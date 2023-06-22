[![CircleCI](https://circleci.com/gh/boxheed/gradle-gitignore-plugin/tree/master.svg?style=shield)](https://circleci.com/gh/boxheed/gradle-gitignore-plugin/tree/master)


# Gradle Gitignore Plugin
Simple plugin that provides a task and configuration to write `.gitignore` file.

# Why
This plugin is designed to work with other plugins that are used to generate templates and preconfigured projects that are 'opinionated' about how a project should be set up. This plugin is ideally suited to be used in conjunction with the `gradle-pater-build-plugin` that allows you to define your opinions on how your project should be set up.

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

	//flag whether to merge this into an existing 
	//.gitignore file, defaults to false
	merge false

	//each entry/row you want to have in the 
	//.gitignore file
    ignore 'bin'
    ignore 'build'
    ignore 'another'

	//You can use a URL to obtain the content
	//from a remote source and write it to the 
	//.gitignore file the https://github.com/github/gitignore
	//repository is a great source for this
	url 'https://raw.githubusercontent.com/github/gitignore/main/Gradle.gitignore'
    url 'https://raw.githubusercontent.com/github/gitignore/main/Java.gitignore'
}

```

# Tasks
The plugin defines two tasks: `displayGitignore` and `writeGitignore`. If you run gradle tasks then you will see these two grouped under a Gitignore section.

## writeGitignore
Writes the values from the gitignore configuration to the `.gitignore` file. This honours the `merge` flag in the configuration, by default the `merge` is false i.e. this will overwrite your `.gitignore` file.

## displayGitignore
Displays the contents of the `.gitignore` file if it exists.
