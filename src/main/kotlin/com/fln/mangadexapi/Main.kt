package com.fln.mangadexapi

import com.fln.mangadexapi.entities.ContentRating
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun main() {
  CoroutineScope(Dispatchers.IO).launch { println(Mangadex.mangaTagAsync()) }
  println("Var")
  println(
    Mangadex.mangaRandom {
      contentRating(listOf(ContentRating.pornographic))
      includeTag("97893a4c-12af-4dac-b6be-0dffb353568e")
    }
  )
}
