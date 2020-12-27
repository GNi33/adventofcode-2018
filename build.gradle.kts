import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "lbdot"
version = "0.1"

repositories {
    mavenCentral()
    jcenter()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

plugins {
    kotlin("jvm") version "1.4.21"
    id("org.jetbrains.compose") version "0.2.0-build132"
    id("org.jmailen.kotlinter") version "3.3.0"
    id("io.gitlab.arturbosch.detekt").version("1.15.0")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(compose.desktop.currentOs)

    implementation("org.koin:koin-core:2.2.1")

    implementation("org.reflections", "reflections", "0.9.11")

    // Test Dependencies
    testImplementation("org.koin:koin-test:2.2.1")

    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

compose.desktop {
    application {
        mainClass = "MainKt"
    }
}

detekt {
    toolVersion = "1.15.0"                                 // Version of the Detekt CLI that will be used. When unspecified the latest detekt version found will be used. Override to stay on the same version.
    input = files("src/main/kotlin")     // The directories where detekt looks for source files. Defaults to `files("src/main/java", "src/main/kotlin")`.
    parallel = false                                      // Builds the AST in parallel. Rules are always executed in parallel. Can lead to speedups in larger projects. `false` by default.
    config = files("var/config/detekt/detekt-config.yml")                  // Define the detekt configuration(s) you want to use. Defaults to the default detekt configuration.
    buildUponDefaultConfig = false                        // Interpret config files as updates to the default config. `false` by default.
    baseline = file("var/config/detekt/detekt-baseline.xml")               // Specifying a baseline file. All findings stored in this file in subsequent runs of detekt.
    disableDefaultRuleSets = false                        // Disables all default detekt rulesets and will only run detekt with custom rules defined in plugins passed in with `detektPlugins` configuration. `false` by default.
    debug = false                                         // Adds debug output during task execution. `false` by default.
    ignoreFailures = false                                // If set to `true` the build does not fail when the maxIssues count was reached. Defaults to `false`.
    reports {
        xml {
            enabled = true                                // Enable/Disable XML report (default: true)
            destination = file("build/reports/detekt.xml")  // Path where XML report will be stored (default: `build/reports/detekt/detekt.xml`)
        }
        html {
            enabled = true                                // Enable/Disable HTML report (default: true)
            destination = file("build/reports/detekt.html") // Path where HTML report will be stored (default: `build/reports/detekt/detekt.html`)
        }
        txt {
            enabled = true                                // Enable/Disable TXT report (default: true)
            destination = file("build/reports/detekt.txt") // Path where TXT report will be stored (default: `build/reports/detekt/detekt.txt`)
        }
        custom {
            reportId = "CustomJsonReport"                   // The simple class name of your custom report.
            destination = file("build/reports/detekt.json") // Path where report will be stored
        }
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
