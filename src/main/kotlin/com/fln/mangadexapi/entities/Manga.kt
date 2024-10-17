package com.fln.mangadexapi.entities

import java.util.*

data class Manga(
  val id: String,
  val attributes: MangaAttributes,
  val relationships: List<Relationship>,
)

data class MangaAttributes(
  val title: Map<String, String>,
  val altTitles: List<Map<String, String>>,
  //  val description: JsonElement,
  val isLocked: Boolean,
  val originalLanguage: String,
  val lastVolume: String?,
  val lastChapter: String?,
  val publicationDemographic: PublicationDemographic?,
  val status: Status,
  val year: Int?,
  val contentRating: ContentRating,
  val chapterNumbersResetOnNewVolume: Boolean,
  val availableTranslatedLanguages: List<String?>,
  val latestUploadedChapter: String?,
  val tags: List<Tag>,
  val state: State,
  val version: Int,
  val createdAt: Date,
  val updatedAt: Date,
)
