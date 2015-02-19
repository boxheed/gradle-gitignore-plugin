package com.fizzpod.gradle.plugins.gitignore;

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory

public class GitignorePlugin implements Plugin<Project> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GitignorePlugin.class);

	void apply(Project project) {
		project.extensions.create("gitignore", GitignorePluginExtension);
		project.task([group: 'gitignore'], 'writeGitignore') << {

			new File(".gitignore").withWriter { out ->
				project.gitignore.ignores.each { out.println it }
			}
		}
		
		project.task([group: 'gitignore'], 'displayGitignore') << {
			def gitignoreFile = new File(".gitignore");
			if(gitignoreFile.exists()) {
				println gitignoreFile.text;
			} else {
				println ".gitignore file does not exist";
			}
		}
	}
}