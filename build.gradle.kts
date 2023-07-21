plugins {
    java
    id("org.springframework.boot") version "2.7.12"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
//    implementation("org.springframework.boot:spring-boot-starter-security")
//    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.springframework.security:spring-security-test")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:2.7.5")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:2.3.1")
    implementation ("io.springfox:springfox-boot-starter:3.0.0")
    implementation ("io.springfox:springfox-swagger-ui:3.0.0")

    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
    implementation(group = "io.jsonwebtoken", name = "jjwt-api", version = "0.11.5")
    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl
    runtimeOnly(group = "io.jsonwebtoken", name = "jjwt-impl", version = "0.11.5")
    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson
    runtimeOnly(group = "io.jsonwebtoken", name = "jjwt-jackson", version = "0.11.5")

    // 추가적인 dependencies
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.3.1")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:2.7.5")
    // ... 추가적인 dependencies 추가
}

tasks.withType<Test> {
    useJUnitPlatform()
}
