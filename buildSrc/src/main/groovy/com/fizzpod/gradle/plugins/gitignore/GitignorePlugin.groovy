package com.fizzpod.gradle.plugins.gitignore

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GitignorePlugin implements Plugin<Project> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GitignorePlugin.class)

	void apply(Project project) {
		project.extensions.create("gitignore", GitignorePluginExtension)
		project.task([group: 'gitignore'], 'writeGitignore').doLast {
			GitignoreFile gitignoreFile = new GitignoreFile()
			LOGGER.info(".gitignore contents before writing: ")
			LOGGER.info(gitignoreFile.getContents(project.getRootDir()))
			gitignoreFile.write(project.getRootDir(), project.gitignore)
			LOGGER.info(".gitignore contents after writing: ")
			LOGGER.info(gitignoreFile.getContents(project.getRootDir()))
		}

		project.task([group: 'gitignore'], 'displayGitignore').doLast {
			String contents = new GitignoreFile().getContents(project.getRootDir())
			if(contents) {
				println contents
			} else {
				println ".gitignore file is either empty or does not exist."
			}
		}
	}
}
