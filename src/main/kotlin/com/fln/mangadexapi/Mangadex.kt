@file:Suppress("unused")

package com.fln.mangadexapi

import com.fln.mangadexapi.entities.CollectionResponse
import com.fln.mangadexapi.entities.ContentRating
import com.fln.mangadexapi.entities.EntityResponse
import com.fln.mangadexapi.entities.Manga
import com.fln.mangadexapi.entities.Tag
import com.fln.mangadexapi.entities.TagQueryMode
import java.text.SimpleDateFormat
import java.util.Date
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.coroutines.executeAsync

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
            encoder.encodeString(
              SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(value)
            )
          }

          override fun deserialize(decoder: Decoder): Date {
            val dateString = decoder.decodeString()
            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
              .parse(dateString)
          }
        }
      )
    }
  }

  class MangaRandomBuilder(builder: MangaRandomBuilder.() -> Unit) :
    IBuilder<Manga> {
    private var contentRating =
      listOf(
        ContentRating.safe,
        ContentRating.suggestive,
        ContentRating.erotica,
      )
    private val includedTags = listOf<String>()
    private var includedTagsMode = TagQueryMode.AND
    private val excludedTags = listOf<String>()
    private var excludedTagsMode = TagQueryMode.OR

    fun contentRating(value: List<ContentRating>) {
      this.contentRating = value
    }

    fun includeTag(value: String) {
      this.includedTags + value
    }

    fun excludeTag(value: String) {
      this.excludedTags + value
    }

    fun includeMode(value: TagQueryMode) {
      this.includedTagsMode = value
    }

    override fun createRequest(): Request {
      val url =
        "$BASE_URL/manga/random"
          .toHttpUrl()
          .newBuilder()
          .apply {
            for (ct in contentRating) {
              addQueryParameter("contentRating[]", ct.name)
            }
            for (i in includedTags) {
              addQueryParameter("includedTags[]", i)
            }
            for (e in excludedTags) {
              addQueryParameter("excludedTags[]", e)
            }
            addQueryParameter("includedTagsMode", includedTagsMode.name)
            addQueryParameter("excludedTagsMode", excludedTagsMode.name)
          }
          .build()
      return Request.Builder().apply { url(url) }.build()
    }

    override fun handleResponse(response: Response): Manga? {
      return response.body.use {
        json.decodeFromString<EntityResponse<Manga>>(it.string()).data
      }
    }

    override fun build(): Manga? {
      return client.newCall(createRequest()).execute().use {
        handleResponse(it)
      }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun buildAsync(): Manga? {
      return client.newCall(createRequest()).executeAsync().use {
        handleResponse(it)
      }
    }
  }

  fun mangaRandom(builder: MangaRandomBuilder.() -> Unit = {}): Manga? {
    return MangaRandomBuilder(builder).build()
  }

  suspend fun mangaRandomAsync(
    builder: MangaRandomBuilder.() -> Unit = {}
  ): Manga? {
    return MangaRandomBuilder(builder).buildAsync()
  }

  class MangaTagBuilder(builder: MangaTagBuilder.() -> Unit) :
    IBuilder<List<Tag>> {
    override fun createRequest(): Request {
      return Request.Builder().url("$BASE_URL/manga/tag").build()
    }

    override fun handleResponse(response: Response): List<Tag>? {
      return response.body.use {
        json.decodeFromString<CollectionResponse<Tag>>(it.string()).data
      }
    }

    override fun build(): List<Tag>? {
      return client.newCall(createRequest()).execute().use {
        handleResponse(it)
      }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun buildAsync(): List<Tag>? {
      return client.newCall(createRequest()).executeAsync().use {
        handleResponse(it)
      }
    }
  }

  fun mangaTag(builder: MangaTagBuilder.() -> Unit = {}): List<Tag>? {
    return MangaTagBuilder(builder).build()
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  suspend fun mangaTagAsync(
    builder: MangaTagBuilder.() -> Unit = {}
  ): List<Tag>? {
    return MangaTagBuilder(builder).buildAsync()
  }
}

interface IBuilder<T> {
  fun createRequest(): Request

  fun handleResponse(response: Response): T?

  fun build(): T?

  suspend fun buildAsync(): T?
}
