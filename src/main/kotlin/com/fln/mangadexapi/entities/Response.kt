package com.fln.mangadexapi.entities

data class EntityResponse<T>(val data: T)

data class CollectionResponse<T>(
  val data: List<T>,
  val limit: Int,
  val offset: Int,
  val total: Int,
)

data class ErrorResponse(val errors: List<Error>) : Throwable()

data class Error(
  val id: String,
  val status: Int,
  val title: String,
  val detail: String?,
  val context: String?,
)
