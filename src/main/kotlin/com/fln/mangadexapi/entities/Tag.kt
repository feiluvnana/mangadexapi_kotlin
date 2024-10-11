@file:Suppress("EnumEntryName")

package com.fln.mangadexapi.entities

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Tag(
  val id: String,
  val attributes: TagAttributes,
  val relationships: List<Relationship>,
)

@Serializable
data class TagAttributes(
  val name: Map<String, String>,
  val description: JsonElement,
  val group: TagGroup,
  val version: Int,
)

enum class TagGroup {
  content,
  format,
  genre,
  theme,
}
