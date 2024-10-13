@file:Suppress("unused")

package com.fln.mangadexapi

import com.fln.mangadexapi.entities.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.*
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.coroutines.executeAsync
import java.text.SimpleDateFormat
import java.util.Date

object Mangadex {
  const val BASE_URL = "https://api.mangadex.org"

  private val client = OkHttpClient.Builder().build()
  private val json = Json {
    explicitNulls = false
    ignoreUnknownKeys = true
    serializersModule = SerializersModule {
      contextual<Date>(
        object : KSerializer<Date> {
          override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

          override fun serialize(encoder: Encoder, value: Date) {
            encoder.encodeString(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(value))
          }

          override fun deserialize(decoder: Decoder): Date {
            val dateString = decoder.decodeString()
            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(dateString)
          }
        }
      )
    }
  }

  abstract class IBuilder<T>() {
    abstract fun request(): Request

    abstract fun response(response: String): T

    @OptIn(ExperimentalSerializationApi::class)
    fun build(): T {
      val stringResponse = client.newCall(request()).execute().body.string()
      try {
        return response(stringResponse)
      } catch (e: MissingFieldException) {
        throw json.decodeFromString<ErrorResponse>(stringResponse)
      }
    }

    @OptIn(ExperimentalSerializationApi::class)
    suspend fun buildAsync(): T {
      val stringResponse = client.newCall(request()).executeAsync().body.string()
      try {
        return response(stringResponse)
      } catch (e: MissingFieldException) {
        throw json.decodeFromString<ErrorResponse>(stringResponse)
      }
    }
  }

  class AtHome(val chapterId: String, builder: AtHome.() -> Unit) : IBuilder<AtHomeResponse>() {
    var forcePort443 = false

    override fun request(): Request {
      return Request.Builder()
        .url("$BASE_URL/at-home/server/$chapterId?forcePort443=$forcePort443")
        .build()
    }

    override fun response(response: String): AtHomeResponse {
      return json.decodeFromString<AtHomeResponse>(response)
    }
  }

  class MangaRandom(builder: MangaRandom.() -> Unit) : IBuilder<Manga>() {
    var contentRating = listOf(ContentRating.safe, ContentRating.suggestive, ContentRating.erotica)
    private val includedTags = listOf<String>()
    var includedTagsMode = TagQueryMode.AND
    private val excludedTags = listOf<String>()
    var excludedTagsMode = TagQueryMode.OR

    fun includeTag(value: String) {
      this.includedTags + value
    }

    fun excludeTag(value: String) {
      this.excludedTags + value
    }

    override fun request(): Request {
      val url =
        "$BASE_URL/manga/random"
          .toHttpUrl()
          .newBuilder()
          .apply {
            for (ct in contentRating) addQueryParameter("contentRating[]", ct.name)
            for (i in includedTags) addQueryParameter("includedTags[]", i)
            for (e in excludedTags) addQueryParameter("excludedTags[]", e)
            addQueryParameter("includedTagsMode", includedTagsMode.name)
            addQueryParameter("excludedTagsMode", excludedTagsMode.name)
          }
          .build()
      return Request.Builder().apply { url(url) }.build()
    }

    override fun response(response: String): Manga {
      return json.decodeFromString<EntityResponse<Manga>>(response).data
    }
  }

  class MangaTag(builder: MangaTag.() -> Unit) : IBuilder<List<Tag>>() {
    override fun request(): Request {
      return Request.Builder().url("$BASE_URL/manga/tag").build()
    }

    override fun response(response: String): List<Tag> {
      return json.decodeFromString<CollectionResponse<Tag>>(response).data
    }
  }
}
