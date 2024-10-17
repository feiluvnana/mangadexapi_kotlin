plugins {
  kotlin("jvm") version "2.0.21"
  `maven-publish`
}

group = "org.fln"

version = "1.0-SNAPSHOT"

repositories { mavenCentral() }

dependencies {
  implementation("com.squareup.retrofit2:retrofit:2.11.0")
  implementation("com.squareup.retrofit2:converter-gson:2.11.0")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
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
