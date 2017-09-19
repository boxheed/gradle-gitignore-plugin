package com.fizzpod.gradle.plugins.gitignore

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import static org.hamcrest.core.IsNull.nullValue
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder
import static org.junit.Assert.assertThat

class GitignoreFileTest {

	@Rule
	public TemporaryFolder folder= new TemporaryFolder()

	@Test
	void testReadMissingGitignoreFile() {
		def contents = new GitignoreFile().getContents(folder.root)
		assertThat(contents, nullValue())
	}
	
	@Test
	void testReadEmptyGitignoreFile() {
		writeGitignore()
		String contents = new GitignoreFile().getContents(folder.root)
		assertThat(contents, isEmptyOrNullString())
	}
	
	@Test
	void testReadGitignoreFileWithContent() {
		writeGitignore("abc", "def")
		def contents = new GitignoreFile().getContents(folder.root)
		assertThat(contents, stringContainsInOrder(Arrays.asList("abc", "def")))
	}
	
	@Test
	void writeGitignoreFile() {
		new GitignoreFile().writeContents(folder.getRoot(), ["123", "abc"])
		String content = new File(folder.getRoot(), ".gitignore").text
		assertThat(content, stringContainsInOrder(Arrays.asList("123","abc")))
	}
	
	private void writeGitignore(String[] lines) {
		new File(folder.getRoot(), ".gitignore").withWriter { out ->
			lines.each { out.println it }
		}
	}
	
}
