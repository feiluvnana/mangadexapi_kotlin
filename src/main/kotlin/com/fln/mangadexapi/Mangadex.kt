@file:Suppress("unused")

package com.fln.mangadexapi

import com.fln.mangadexapi.entities.AtHome
import com.fln.mangadexapi.entities.Author
import com.fln.mangadexapi.entities.CollectionResponse
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object Mangadex {
  private const val BASE_URL = "https://api.mangadex.org"
  @OptIn(ExperimentalUuidApi::class)
  val unauth: MangadexUnauthenticatedApi =
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(
        GsonConverterFactory.create(
          GsonBuilder()
            .registerTypeAdapter(
              Uuid::class.java,
              object : TypeAdapter<Uuid>() {
                override fun write(out: JsonWriter?, value: Uuid?) {
                  TODO("Not yet implemented")
                }

                override fun read(`in`: JsonReader?): Uuid {
                  TODO("Not yet implemented")
                }
              },
            )
            .create()
        )
      )
      .build()
      .create(MangadexUnauthenticatedApi::class.java)
}

interface MangadexUnauthenticatedApi {
  @OptIn(ExperimentalUuidApi::class)
  @GET("/at-home/server/{id}")
  suspend fun atHome(@Path("id") chapterId: Uuid): Response<AtHome>

  @OptIn(ExperimentalUuidApi::class)
  @GET("/author")
  suspend fun authors(
    @Query("limit") limit: Int = 10,
    @Query("offset") offset: Int? = null,
    @Query("ids[]") ids: List<Uuid>? = null,
    @Query("name") name: Int? = null,
  ): Response<CollectionResponse<Author>>
}

//  private val json = Json {
//    explicitNulls = false
//    ignoreUnknownKeys = true
//    serializersModule = SerializersModule {
//      contextual<Date>(
//        object : KSerializer<Date> {
//          override val descriptor: SerialDescriptor
//            get() = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)
//
//          override fun serialize(encoder: Encoder, value: Date) {
//            encoder.encodeString(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(value))
//          }
//
//          override fun deserialize(decoder: Decoder): Date {
//            val dateString = decoder.decodeString()
//            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(dateString)
//          }
//        }
//      )
//    }
//  }
