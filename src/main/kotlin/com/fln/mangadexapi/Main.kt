package com.fln.mangadexapi

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun main() {
  val file = File("output.json")
  file.appendText("[")
  file.appendText(
    Json.encodeToString(Mangadex.AtHome("9084b863-ebef-4a13-8e0c-c4dffe85d210") {}.build())
  )
  file.appendText(",")
  println(Mangadex.MangaTag {}.build())
  print(Mangadex.MangaRandom {}.build())
  file.appendText("]")
}
