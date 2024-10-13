package com.fln.mangadexapi.entities

import kotlinx.serialization.Serializable

@Serializable data class EntityResponse<T>(val data: T)

@Serializable
data class CollectionResponse<T>(
  val data: List<T>,
  val limit: Int,
  val offset: Int,
  val total: Int,
)

@Serializable data class ErrorResponse(val errors: List<Error>) : Throwable()

@Serializable
data class Error(
  val id: String,
  val status: Int,
  val title: String,
  val detail: String?,
  val context: String?,
)
