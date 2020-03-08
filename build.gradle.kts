plugins {
    java
    id("org.springframework.boot") version "2.2.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

configurations["implementation"].isTransitive = false
configurations["testImplementation"].isTransitive = false

tasks.withType<Test> {
    useJUnitPlatform {
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}
