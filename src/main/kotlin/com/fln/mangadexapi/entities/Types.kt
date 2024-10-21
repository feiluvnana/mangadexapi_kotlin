@file:Suppress("EnumEntryName")

package com.fln.mangadexapi.entities

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.util.*

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

enum class QueryMode {
  and,
  or,
}

enum class OrderMode {
  asc,
  desc,
}

enum class LanguageCode(val code: String) {
  afrikaans("af"),
  albanian("sq"),
  arabic("ar"),
  armenian("hy"),
  azerbaijani("az"),
  basque("eu"),
  belarusian("be"),
  bengali("bn"),
  bosnian("bs"),
  bulgarian("bg"),
  catalan("ca"),
  simplified_chinese("zh"),
  croatian("hr"),
  czech("cs"),
  danish("da"),
  dutch("nl"),
  english("en"),
  esperanto("eo"),
  estonian("et"),
  finnish("fi"),
  french("fr"),
  galician("gl"),
  georgian("ka"),
  german("de"),
  greek("el"),
  gujarati("gu"),
  haitian_creole("ht"),
  hebrew("he"),
  hindi("hi"),
  hungarian("hu"),
  icelandic("is"),
  indonesian("id"),
  irish("ga"),
  italian("it"),
  japanese("ja"),
  javanese("jv"),
  kazakh("kk"),
  khmer("km"),
  korean("ko"),
  kurdish("ku"),
  kyrgyz("ky"),
  lao("lo"),
  latvian("lv"),
  lithuanian("lt"),
  macedonian("mk"),
  malay("ms"),
  maltese("mt"),
  maori("mi"),
  marathi("mr"),
  mongolian("mn"),
  nepali("ne"),
  norwegian("no"),
  pashto("ps"),
  persian("fa"),
  polish("pl"),
  portuguese("pt"),
  punjabi("pa"),
  romanian("ro"),
  russian("ru"),
  serbian("sr"),
  slovak("sk"),
  slovenian("sl"),
  somali("so"),
  castilian_spanish("es"),
  swahili("sw"),
  swedish("sv"),
  tamil("ta"),
  telugu("te"),
  thai("th"),
  turkish("tr"),
  ukrainian("uk"),
  urdu("ur"),
  uzbek("uz"),
  vietnamese("vi"),
  welsh("cy"),
  xhosa("xh"),
  yiddish("yi"),
  zulu("zu"),
  traditional_chinese("zh-hk"),
  brazilian_portuguese("pt-br"),
  latin_american_spanish("es-la"),
  romanized_japanese("ja-ro"),
  romainzed_korean("ko-ro"),
  romanized_chinese("zh-ro");

  companion object {
    fun fromCode(code: String): LanguageCode {
      return entries.find { it.code.equals(code, ignoreCase = true) } ?: english
    }

    val adapter: TypeAdapter<LanguageCode> =
      object : TypeAdapter<LanguageCode>() {
        override fun write(out: JsonWriter?, value: LanguageCode?) {
          out?.value(value?.code)
        }

        override fun read(`in`: JsonReader?): LanguageCode {
          return fromCode(`in`?.nextString() ?: "")
        }
      }
  }
}

class AuthorOrderBuilder(val builder: AuthorOrderBuilder.() -> Unit) {
  var name: OrderMode? = null

  fun build(): Map<String, String> {
    return { "name" to name?.name?.lowercase(Locale.getDefault()) }
  }
}
