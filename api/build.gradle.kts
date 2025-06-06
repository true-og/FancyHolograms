plugins {
    id("java-library")
    id("maven-publish")
    id("io.github.goooler.shadow") version "8.1.7"
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")

    compileOnly("de.oliver:FancyLib:${findProperty("fancyLibVersion")}")

    compileOnly("org.lushplugins:ChatColorHandler:4.0.0")

}

tasks {
    shadowJar {
        relocate("me.dave.chatcolorhandler", "de.oliver.fancyholograms.libs.chatcolorhandler")

        archiveClassifier.set("")
    }

    publishing {
        repositories {
            maven {
                name = "fancypluginsReleases"
                url = uri("https://repo.fancyplugins.de/releases")
                credentials(PasswordCredentials::class)
                authentication {
                    isAllowInsecureProtocol = true
                    create<BasicAuthentication>("basic")
                }
            }

            maven {
                name = "fancypluginsSnapshots"
                url = uri("https://repo.fancyplugins.de/snapshots")
                credentials(PasswordCredentials::class)
                authentication {
                    isAllowInsecureProtocol = true
                    create<BasicAuthentication>("basic")
                }
            }
        }
        publications {
            create<MavenPublication>("maven") {
                groupId = rootProject.group.toString()
                artifactId = rootProject.name
                version = rootProject.version.toString()
                from(project.components["java"])
            }
        }
    }

    java {
        withSourcesJar()
        withJavadocJar()
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()

        options.release.set(17)
    }
}
