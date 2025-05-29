package com.github.hummel.mmpu.curseforge

import com.github.hummel.mmpu.Config
import com.github.hummel.mmpu.extractMcVersion
import com.github.hummel.mmpu.extractModLoader
import com.github.hummel.mmpu.sortAlphabetically
import com.google.gson.Gson
import org.apache.hc.client5.http.classic.methods.HttpGet
import org.apache.hc.client5.http.classic.methods.HttpPost
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.core5.http.ContentType
import org.apache.hc.core5.http.io.entity.EntityUtils
import java.io.File

fun main() {
	val config = Gson().fromJson(File("config.json").readText(), Config::class.java)
	val idToVersion = getMappingsFromAPI(config.token)

	config.getMapping().forEach { idToName ->
		publishProject(idToName, idToVersion, config.token)
		println("All project files were uploaded. Press any key to continue...")
		readln()
	}
}

private fun publishProject(
	idToName: Map.Entry<String, String>, idToVersion: Map<Int, String>, token: String
) {
	val files = File("folders/${idToName.value}").listFiles() ?: return

	files.sortAlphabetically()

	files.forEach { jar ->
		val (mcVersionId, modLoaderId) = getJarInfo(jar, idToVersion)
		val serverSidenessId = 9639
		val clientSidenessId = 9638

		HttpClients.createDefault().use {
			val url = "https://minecraft.curseforge.com/api/projects/${idToName.key}/upload-file"

			val request = HttpPost(url)
			request.addHeader("X-Api-Token", token)

			val multipartEntityBuilder = MultipartEntityBuilder.create()
			multipartEntityBuilder.addTextBody(
				"metadata", """
				{
					"changelog": "",
					"gameVersions": [$mcVersionId, $modLoaderId, $serverSidenessId, $clientSidenessId],
					"releaseType": "release"
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

private val modLoaderMappings: Map<String, Int> = mapOf(
	"NeoForge" to 10150,
	"Forge" to 7498,
	"Fabric" to 7499,
	"Quilt" to 9153
)

private fun getJarInfo(
	jar: File, idToVersion: Map<Int, String>
): Pair<Int, Int> {
	val mcVersion = jar.name.extractMcVersion()
	val modLoader = jar.name.extractModLoader()

	var mcVersionId = 0

	for ((id, value) in idToVersion) {
		if (value == mcVersion) {
			mcVersionId = id
			break
		}
	}

	require(mcVersionId != 0)

	return mcVersionId to (modLoaderMappings[modLoader] ?: error("Wrong ModLoader ID!"))
}

private fun getMappingsFromAPI(token: String): Map<Int, String> {
	HttpClients.createDefault().use {
		val request = HttpGet("https://minecraft.curseforge.com/api/game/versions")
		request.addHeader("X-Api-Token", token)

		val response = it.execute(request) { response ->
			val entity = response.entity

			EntityUtils.toString(entity)
		}

		return@getMappingsFromAPI parseMappings(response)
	}
}

private fun parseMappings(json: String): Map<Int, String> {
	val gson = Gson()

	val listType = object : com.google.gson.reflect.TypeToken<List<GameVersion>>() {}.type
	val gameVersions = gson.fromJson<List<GameVersion>>(json, listType)

	val idToVer = mutableMapOf<Int, String>()

	gameVersions.forEach { idToVer[it.id] = it.name }

	return idToVer
}