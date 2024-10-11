@file:Suppress("EnumEntryName")

package com.fln.mangadexapi.entities

enum class State {
  draft,
  submitted,
  published,
  rejected,
}

enum class ContentRating {
  safe,
  suggestive,
  erotica,
  pornographic,
}

enum class Status {
  completed,
  ongoing,
  cancelled,
  hiatus,
}

enum class PublicationDemographic {
  shounen,
  shoujo,
  josei,
  seinen,
  none,
}

enum class TagQueryMode {
  AND,
  OR,
}
