@file:Suppress("EnumEntryName")

package com.fln.mangadexapi.entities

data class Relationship(
  val id: String,
  val type: RelationshipType,
  val related: RelationshipRelated?,
)

enum class RelationshipType {
  manga,
  chapter,
  cover_art,
  author,
  artist,
  scanlation_group,
  tag,
  user,
  custom_list,
  creator,
  leader,
  member,
}

enum class RelationshipRelated {
  monochrome,
  main_story,
  adapted_from,
  based_on,
  prequel,
  side_story,
  doujinshi,
  same_franchise,
  shared_universe,
  sequel,
  spin_off,
  alternate_story,
  alternate_version,
  preserialization,
  colored,
  serialization,
}
