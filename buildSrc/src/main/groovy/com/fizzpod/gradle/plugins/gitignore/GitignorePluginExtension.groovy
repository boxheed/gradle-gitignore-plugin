package com.fizzpod.gradle.plugins.gitignore;

public class GitignorePluginExtension {
	List<String> ignores = new ArrayList<String>();
	
	def ignore(String ignore) {
		ignores << ignore;
		return this;
	}
}
