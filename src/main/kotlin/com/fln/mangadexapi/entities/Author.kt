package com.fln.mangadexapi.entities

import kotlinx.serialization.*
import kotlinx.serialization.json.JsonElement
import java.util.Date

@Serializable
data class Author(
  val id: String,
  val authorAttributes: AuthorAttributes,
  val relationships: List<Relationship>,
)

@Serializable
data class AuthorAttributes(
  val name: String,
  val imageUrl: String?,
  val biography: JsonElement,
  val twitter: String?,
  val pixiv: String?,
  val melonBook: String?,
  val fanBox: String?,
  val booth: String?,
  val nicoVideo: String?,
  val skeb: String?,
  val fantia: String?,
  val tumblr: String?,
  val youtube: String?,
  val weibo: String?,
  val naver: String?,
  val namicomi: String?,
  val website: String?,
  val version: Int,
  @Contextual val createdAt: Date,
  @Contextual val updatedAt: Date,
)
