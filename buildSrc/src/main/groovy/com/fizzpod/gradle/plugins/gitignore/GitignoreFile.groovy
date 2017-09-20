package com.fizzpod.gradle.plugins.gitignore

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

class GitignoreFile {
	public static final String FILE_NAME = ".gitignore"

	String getContents(File folder) {
		def gitignoreFile = new File(folder, FILE_NAME);
		def contents = null;
		if (gitignoreFile.exists()) {
			contents = gitignoreFile.text;
		}
		return contents;
	}

	void writeContents(File folder, Collection<String> contents, boolean merge = false) {
		final def ignoreFilePath = Paths.get(folder.toPath().toString(), FILE_NAME)
		if (merge) {
			contents = Files.readAllLines(ignoreFilePath, Charset.defaultCharset()) + contents
		}
		ignoreFilePath.toFile().withWriter { out ->
			contents.each { out.println it }
		}
	}

}
