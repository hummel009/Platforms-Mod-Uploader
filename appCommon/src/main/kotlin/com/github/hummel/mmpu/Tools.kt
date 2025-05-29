package com.github.hummel.mmpu

import java.io.File

fun String.extractMcVersion(): String {
	val regex = Regex("""\[(.+)\]""")
	val match = regex.find(this)
	return match?.groupValues?.get(1) ?: ""
}

fun String.extractModLoader(): String {
	val regex = Regex("""\((.+)\)""")
	val match = regex.find(this)
	return match?.groupValues?.get(1) ?: ""
}

fun String.extractModVersion(): String {
	val regex = Regex("""(\d{2}\.\d{2}\.\d{2})""")
	val match = regex.find(this)
	return match?.groupValues?.get(1) ?: ""
}

fun Array<out File>.sortAlphabetically() {
	try {
		val fileComparatorMinor = Comparator<File> { file1, file2 ->
			val version1 = file1.name.extractMcVersion()
			val version2 = file2.name.extractMcVersion()

			val parts1 = version1.split('.').map { it.toInt() }
			val parts2 = version2.split('.').map { it.toInt() }

			(0 until 3).asSequence().map {
				parts1[it].compareTo(parts2[it])
			}.firstOrNull {
				it != 0
			} ?: 0
		}

		sortWith(fileComparatorMinor)
	} catch (_: Exception) {
		val fileComparatorMinor = Comparator<File> { file1, file2 ->
			val version1 = file1.name.extractMcVersion()
			val version2 = file2.name.extractMcVersion()

			val parts1 = version1.split('.').map { it.toInt() }
			val parts2 = version2.split('.').map { it.toInt() }

			(0 until 2).asSequence().map {
				parts1[it].compareTo(parts2[it])
			}.firstOrNull {
				it != 0
			} ?: 0
		}

		sortWith(fileComparatorMinor)
	}
}