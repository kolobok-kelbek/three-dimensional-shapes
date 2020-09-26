plugins {
    application
}

group = "com.kelbek"
version = "0.1.0"

repositories {
    mavenCentral()
}

(tasks.getByName("processResources") as ProcessResources).apply {
    filesMatching("application.properties") {
        expand(project.properties)
    }
}

dependencies {
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter
    implementation("org.springframework.boot:spring-boot-starter:2.3.2.RELEASE")

    // Lombok
    compileOnly ("org.projectlombok:lombok:1.18.12")
    annotationProcessor ("org.projectlombok:lombok:1.18.12")

    // Lightweight Java Game Library (Graphics libraries)
    implementation ("org.lwjgl:lwjgl:3.2.1")
    implementation ("org.lwjgl.osgi:org.lwjgl.glfw:3.2.1")
    implementation ("org.lwjgl.osgi:org.lwjgl.opengl:3.2.1")
    implementation ("org.lwjgl.osgi:org.lwjgl.stb:3.2.1")
    implementation ("org.lwjgl.osgi:org.lwjgl.nanovg:3.2.1")
    implementation ("org.lwjgl.osgi:org.lwjgl.assimp:3.2.1")

    // JOML
    implementation ("org.joml:joml:1.9.25")

    // Test libraries
    testImplementation ("junit:junit:4.12")
}

application {
    setMainClassName("com.kelbek.three.dimensional.shares.Application")
}
