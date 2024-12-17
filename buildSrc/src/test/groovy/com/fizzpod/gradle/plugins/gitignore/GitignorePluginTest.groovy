/* (C) 2024 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.gitignore

import org.gradle.api.Project
import org.gradle.api.tasks.TaskContainer
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GitignorePluginTest {

	private Project project
	private GitignorePlugin plugin

	@Before
	void setup() {
		project = ProjectBuilder.builder().build()
		plugin = new GitignorePlugin()
	}
	
	
	@Test
	void basicPluginConfigurationTest() {
		plugin.apply(project)
		GitignorePluginExtension extension = project.extensions.findByName("gitignore")
		Assert.assertTrue(extension instanceof GitignorePluginExtension)
		Collection<String> ignores = extension.getIgnores()
		Assert.assertNotNull(ignores)
		Assert.assertTrue(ignores.isEmpty())
		TaskContainer taskContainer = project.getTasks()
		Assert.assertEquals(2, taskContainer.findAll().size())
	}
	
}
