plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.13"
}

group = "com.peterpl"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20250517")
}

javafx {
    version = "21"
    modules("javafx.controls", "javafx.swing")
}

application {
    mainClass = "com.peterpl.mypaint.MyPaint"
}