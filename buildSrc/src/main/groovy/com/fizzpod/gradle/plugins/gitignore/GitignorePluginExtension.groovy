/* (C) 2024 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.gitignore

public class GitignorePluginExtension {
	Collection<String> ignores = new LinkedHashSet<String>()
	Collection<String> urls = new LinkedHashSet<String>()
	boolean merge = false

	def merge(boolean merge){
		this.merge = merge
		return this
	}

	def ignore(String ignore) {
		ignores.add(ignore)
		return this
	}

	def url(String url) {
		urls.add(url)
		return this
	}
}
