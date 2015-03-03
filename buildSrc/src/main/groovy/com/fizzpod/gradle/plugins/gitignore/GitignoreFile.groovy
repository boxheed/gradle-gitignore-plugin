package com.fizzpod.gradle.plugins.gitignore

class GitignoreFile {

	String getContents(File folder) {
		def gitignoreFile = new File(folder, ".gitignore");
		def contents = null;
		if(gitignoreFile.exists()) {
			contents = gitignoreFile.text;
		} 
		return contents;
	}
	
	void writeContents(File folder, def contents) {
		new File(folder, ".gitignore").withWriter { out ->
			contents.each { out.println it }
		}
	}
	
}
