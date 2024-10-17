package com.fln.mangadexapi.entities

data class AtHome(val baseUrl: String, val chapter: AtHomeChapter)

data class AtHomeChapter(val hash: String, val data: List<String>, val dataSaver: List<String>)
