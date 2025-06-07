import org.gradle.kotlin.dsl.compileOnly

plugins {
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.spring") version "2.1.10"
    id("org.springframework.boot") version "3.4.6"
    id("io.spring.dependency-management") version "1.1.6"
    id("gg.jte.gradle") version "3.2.1"
}

group = "dev.kartikboreda"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
//    implementation("org.springframework.boot:spring-boot-starter-data-ldap")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("gg.jte:jte:3.2.1")
    // https://mvnrepository.com/artifact/net.sf.trove4j/trove4j
    implementation("net.sf.trove4j:trove4j:2.1.0")
    compileOnly("gg.jte:jte-kotlin:3.2.1")
    implementation("gg.jte:jte-spring-boot-starter-3:3.2.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("com.unboundid:unboundid-ldapsdk")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

jte {
    generate()
    binaryStaticContent = true
    contentType = gg.jte.ContentType.Html
}

tasks.withType<Test> {
    useJUnitPlatform()
}

