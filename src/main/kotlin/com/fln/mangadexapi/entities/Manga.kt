package com.fln.mangadexapi.entities

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import java.util.Date

@Serializable
data class Manga(
  val id: String,
  val attributes: MangaAttributes,
  val relationships: List<Relationship>,
)

@Serializable
data class MangaAttributes(
  val title: Map<String, String>,
  val altTitles: List<Map<String, String>>,
  val description: JsonElement,
  val isLocked: Boolean,
  val links: JsonElement,
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
  @Contextual val createdAt: Date,
  @Contextual val updatedAt: Date,
)
