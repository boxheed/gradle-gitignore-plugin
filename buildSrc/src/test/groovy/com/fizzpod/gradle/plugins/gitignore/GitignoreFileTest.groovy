/* (C) 2024-2025 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.gitignore

import static org.hamcrest.core.IsNull.nullValue
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder
import static org.junit.Assert.assertThat

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class GitignoreFileTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder()

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
        def extension = new GitignorePluginExtension()
        extension.ignore("123").ignore("abc")
        new GitignoreFile().write(folder.getRoot(), extension)
        String content = new File(folder.getRoot(), ".gitignore").text
        assertThat(content, stringContainsInOrder(Arrays.asList("123","abc")))
    }

    @Test
    void testReadGitignoreFileWithMergeContent() {
        writeGitignore("abc", "123")
        def extension = new GitignorePluginExtension()
        extension.ignore("def").ignore("123").merge(true)
        new GitignoreFile().write(folder.getRoot(), extension)

        def contents = new GitignoreFile().getContents(folder.root)
        println(contents)
        assertThat(contents, stringContainsInOrder(Arrays.asList("abc", "123", "def")))
    }


    private void writeGitignore(String[] lines) {
        new File(folder.getRoot(), ".gitignore").withWriter { out ->
            lines.each { out.println it }
        }
    }
    
}
