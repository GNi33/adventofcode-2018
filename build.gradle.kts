import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "lbdot"
version = "0.1"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

plugins {
    kotlin("jvm") version "2.1.0"
    id("io.gitlab.arturbosch.detekt").version("1.23.7")
    application
}

dependencies {

    implementation(project.dependencies.platform("io.insert-koin:koin-bom:3.5.6"))
    implementation("io.insert-koin:koin-core")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.10")
    implementation("org.reflections:reflections:0.10.2")

    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")
    implementation("ch.qos.logback:logback-classic:1.4.12")

    // Test Dependencies
    testImplementation("io.insert-koin:koin-test")

    testImplementation(platform("org.junit:junit-bom:5.9.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("app.MainKt")
}

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    config.setFrom(file("$projectDir/config/detekt/detekt.yml")) // point to your custom config defining rules to run, overwriting default behavior
    baseline = file("$projectDir/config/detekt/baseline.xml") // a way of suppressing issues before introducing detekt
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true) // observe findings in your browser with structure and code snippets
        xml.required.set(false) // checkstyle like format mainly for integrations like Jenkins
        txt.required.set(true) // similar to the console output, contains issue signature to manually edit baseline files
        sarif.required.set(false) // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with GitHub Code Scanning
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "17"
}
tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = "17"
}
