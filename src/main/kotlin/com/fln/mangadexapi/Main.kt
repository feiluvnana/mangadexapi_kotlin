package com.fln.mangadexapi

import com.google.gson.Gson
import java.io.File
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
suspend fun main() {
  val file = File("output.json")
  file.writeText(Gson().toJson(Mangadex.unauth.authors().body()))
}
