package com.fln.mangadexapi.entities

import kotlinx.serialization.Serializable

@Serializable data class AtHomeResponse(val baseUrl: String, val chapter: AtHomeChapter)

@Serializable
data class AtHomeChapter(val hash: String, val data: List<String>, val dataSaver: List<String>)
