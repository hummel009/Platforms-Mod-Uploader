package com.github.hummel.mmpu.modrinth

import com.github.hummel.mmpu.*
import com.google.gson.Gson
import org.apache.hc.client5.http.classic.methods.HttpPost
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.core5.http.ContentType
import org.apache.hc.core5.http.io.entity.EntityUtils
import java.io.File

fun main() {
	val config = Gson().fromJson(File("config.json").readText(), Config::class.java)

	config.getMapping().forEach { idToName ->
		publishProject(idToName, config.token)
		println("All project files were uploaded. Press any key to continue...")
		readln()
	}
}

private fun publishProject(
	idToName: Map.Entry<String, String>, token: String
) {
	val files = File("folders/${idToName.value}").listFiles() ?: return

	files.sortAlphabetically()

	files.forEach { jar ->
		val mcVersion = jar.name.extractMcVersion()
		val modLoader = jar.name.extractModLoader().lowercase()
		val modVersion = jar.name.extractModVersion()

		HttpClients.createDefault().use {
			val url = "https://api.modrinth.com/v2/version"

			val request = HttpPost(url)
			request.addHeader("Authorization", token)
			request.addHeader("User-Agent", "Hummel009/SUS/9.99.0 (h.t@g.c)")

			val multipartEntityBuilder = MultipartEntityBuilder.create()
			multipartEntityBuilder.addTextBody(
				"data", """
				{
					"name": "${jar.name}",
					"version_number": "$modVersion",
					"dependencies": [],
					"version_type": "release",
					"featured": true,
					"project_id": "${idToName.key}",
					"file_parts": ["file"],
					"loaders": ["$modLoader"],
					"game_versions": ["$mcVersion"]
				}
				""".trimIndent(), ContentType.APPLICATION_JSON
			)
			multipartEntityBuilder.addBinaryBody(
				"file", jar, ContentType.DEFAULT_BINARY, jar.name
			)

			request.entity = multipartEntityBuilder.build()

			val response = it.execute(request) { response ->
				val entity = response.entity
				EntityUtils.toString(entity)
			}

			println(response)
		}
	}
}