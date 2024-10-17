package com.fln.mangadexapi.entities

import java.util.Date
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Author
@OptIn(ExperimentalUuidApi::class)
constructor(val id: Uuid, val attributes: AuthorAttributes, val relationships: List<Relationship>)

data class AuthorAttributes(
  val name: String,
  val imageUrl: String?,
  val biography: Map<String, String>,
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
  val createdAt: Date,
  val updatedAt: Date,
)
