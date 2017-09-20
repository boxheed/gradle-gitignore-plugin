package com.fizzpod.gradle.plugins.gitignore;

public class GitignorePluginExtension {
	Collection<String> ignores = new ArrayList<String>();
	boolean merge = false

	def merge(boolean merge){
		this.merge = merge
		return this
	}

	def ignore(String ignore) {
		ignores.add(ignore);
		return this;
	}
}
