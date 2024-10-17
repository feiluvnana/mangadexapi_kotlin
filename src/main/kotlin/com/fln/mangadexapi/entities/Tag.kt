@file:Suppress("EnumEntryName")

package com.fln.mangadexapi.entities

data class Tag(
  val id: String,
  val attributes: TagAttributes,
  val relationships: List<Relationship>,
)

data class TagAttributes(
  val name: Map<String, String>,
  //  val description: JsonElement,
  val group: TagGroup,
  val version: Int,
)

enum class TagGroup {
  content,
  format,
  genre,
  theme,
}
