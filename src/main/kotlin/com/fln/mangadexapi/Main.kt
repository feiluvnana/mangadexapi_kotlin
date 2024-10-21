package com.fln.mangadexapi

import com.google.gson.GsonBuilder
import java.io.File
import kotlinx.coroutines.runBlocking

fun main() {
  val file = File("output.json")
  runBlocking {
    file.writeText(GsonBuilder().setPrettyPrinting().create().toJson(Mangadex.unauth.mangaTags()))
  }
}
