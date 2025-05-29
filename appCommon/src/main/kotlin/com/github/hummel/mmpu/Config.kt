package com.github.hummel.mmpu

data class Config(
	val token: String, val projectIds: List<String>, val projectNames: List<String>
) {
	fun getMapping(): Map<String, String> = projectIds.zip(projectNames).toMap()
}