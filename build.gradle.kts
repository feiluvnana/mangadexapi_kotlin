plugins {
  kotlin("jvm") version "2.0.21"
  kotlin("plugin.serialization") version "2.0.21"
}

group = "org.fln"

version = "1.0-SNAPSHOT"

repositories { mavenCentral() }

dependencies {
  implementation("com.squareup.okhttp3:okhttp:4.12.0")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
  implementation("com.squareup.okhttp3:okhttp-coroutines:5.0.0-alpha.14")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
}

tasks.test { useJUnitPlatform() }
