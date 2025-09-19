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

javafx {
    version = "21"
    modules("javafx.controls")
}

application {
    mainClass = "com.peterpl.mypaint.MyPaint"
}