import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
    id("org.jmailen.kotlinter") version "1.21.0"
    id("io.gitlab.arturbosch.detekt") version("1.0.0-RC14")
}

group = "lbdot"
version = "0.1"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.koin:koin-core:1.0.2")

    implementation("org.reflections", "reflections", "0.9.11")

    // Test Dependencies
    testImplementation("org.koin:koin-test:1.0.2")

    testCompile("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testCompile("org.junit.jupiter:junit-jupiter-params:5.3.2")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.3.2")
}

detekt {
    toolVersion = "1.0.0-RC14"
    input = files("src/main/kotlin")
    config = files("var/config/detekt/detekt-config.yml")
    filters = ".*/resources/.*,.*/build/.*"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
