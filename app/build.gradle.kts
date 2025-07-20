plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    // Plugin para JavaFX
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
     // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation(libs.guava)

    // Dependência corrigida
    implementation("org.openjfx:javafx-controls:21")
    
    // Outras dependências do JavaFX (se necessário)
    implementation("org.openjfx:javafx-fxml:21")
    implementation("org.openjfx:javafx-graphics:21")
    implementation ("com.lukaspradel:steam-web-api:1.11.0")
    implementation ("com.google.code.gson:gson:2.10.1") // Para manipulação de JSON
    
    implementation("org.openjfx:javafx-controls:21")
    implementation("org.openjfx:javafx-graphics:21")
    implementation("org.openjfx:javafx-base:21")
    implementation("org.openjfx:javafx-fxml:21")

    implementation ("com.lukaspradel:steam-web-api:1.11.0")
    implementation ("org.json:json:20230227")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}
javafx {
    version = "21"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    // Altere se o nome do pacote for diferente
    mainClass.set("user_steam.SteamProfileViewer")
}
tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}