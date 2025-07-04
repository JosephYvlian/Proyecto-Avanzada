plugins {
    java
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}


group = "co.edu.uniquindio"
version = "0.0.1-SNAPSHOT"
description = "Breve descripción de la aplicación"


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}


configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}


repositories {
    mavenCentral()
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.0")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.mapstruct:mapstruct:1.6.3")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation ("org.simplejavamail:simple-java-mail:8.11.2")
    implementation ("org.simplejavamail:batch-module:8.10.1")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    implementation("com.google.firebase:firebase-admin:9.1.0")
    implementation("com.cloudinary:cloudinary-http44:1.39.0")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.mockito:mockito-core:5.12.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.12.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")


}


tasks.withType<Test> {
    useJUnitPlatform()
}
