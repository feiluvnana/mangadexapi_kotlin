package com.fln.mangadexapi.entities

import kotlinx.serialization.Serializable

@Serializable data class EntityResponse<T>(val data: T)

@Serializable data class CollectionResponse<T>(val data: List<T>)
