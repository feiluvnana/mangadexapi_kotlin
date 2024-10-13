plugins {
  kotlin("jvm") version "2.0.21"
  kotlin("plugin.serialization") version "2.0.21"
  `maven-publish`
}

group = "org.fln"

version = "1.0-SNAPSHOT"

repositories { mavenCentral() }

dependencies {
  implementation("com.squareup.okhttp3:okhttp:4.12.0")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
  implementation("com.squareup.okhttp3:okhttp-coroutines:5.0.0-alpha.14")
}

publishing {
  publications {
    create<MavenPublication>("maven") {
      groupId = "com.fln"
      artifactId = "mangadexapi"
      version = "1.0.0"

      from(components["java"])
    }
  }
}
