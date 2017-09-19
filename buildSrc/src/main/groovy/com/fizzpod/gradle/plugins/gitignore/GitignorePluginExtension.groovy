package com.fizzpod.gradle.plugins.gitignore;

public class GitignorePluginExtension {
	Collection<String> ignores = new ArrayList<String>();

	def ignore(String ignore) {
		ignores.add(ignore);
		return this;
	}
}
