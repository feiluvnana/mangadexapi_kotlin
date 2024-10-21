@file:Suppress("unused")

package com.fln.mangadexapi

import com.fln.mangadexapi.entities.*
import com.google.gson.GsonBuilder
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object Mangadex {
  private const val BASE_URL = "https://api.mangadex.org"
  val unauth: MangadexUnauthenticatedApi =
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(
        GsonConverterFactory.create(
          GsonBuilder().registerTypeAdapter(LanguageCode::class.java, LanguageCode.adapter).create()
        )
      )
      .addConverterFactory(object : Converter.Factory() {})
      .build()
      .create(MangadexUnauthenticatedApi::class.java)
}

interface MangadexUnauthenticatedApi {

  @GET("/at-home/server/{id}") suspend fun atHome(@Path("id") chapterId: String): AtHome

  @GET("/author")
  suspend fun authors(
    @Query("limit") limit: Int = 10,
    @Query("offset") offset: Int? = null,
    @Query("ids[]") ids: List<String>? = null,
    @Query("name") name: Int? = null,
  ): CollectionResponse<Author>

  @GET("/manga/tag") suspend fun mangaTags(): CollectionResponse<Tag>
}
