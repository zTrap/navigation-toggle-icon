plugins {
    id("maven-publish")
    id("signing")
}

ext["signing.keyId"] = project.findProperty("signing.opensource.key")
ext["signing.password"] = project.findProperty("signing.opensource.password")
ext["signing.secretKeyRingFile"] = project.findProperty("signing.opensource.file")
ext["nexus.username"] = project.findProperty("publishing.nexus.username")
ext["nexus.password"] = project.findProperty("publishing.nexus.password")

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

fun getExtraString(name: String) = ext[name]?.toString()

publishing {
    repositories {
        maven {
            name = "sonatype"
            setUrl("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = getExtraString("nexus.username")
                password = getExtraString("nexus.password")
            }
        }
    }

    publications.withType<MavenPublication> {
        groupId = PublishingInfo.GROUP
        artifactId = PublishingInfo.Artifact.ID
        version = PublishingInfo.Artifact.VERSION

        // Stub javadoc.jar artifact
        artifact(javadocJar.get())

        pom {
            name.set(PublishingInfo.Artifact.NAME)
            description.set(PublishingInfo.Artifact.DESCRIPTION)
            url.set(PublishingInfo.Repo.URL)

            licenses {
                license {
                    name.set(PublishingInfo.License.NAME)
                    url.set(PublishingInfo.License.URL)
                    distribution.set(PublishingInfo.License.DIST)
                }
            }
            developers {
                developer {
                    id.set(PublishingInfo.Developer.ID)
                    name.set(PublishingInfo.Developer.NAME)
                    email.set(PublishingInfo.Developer.EMAIL)
                }
            }
            scm {
                url.set(PublishingInfo.Scm.URL)
                connection.set(PublishingInfo.Scm.CONNECTION)
                developerConnection.set(PublishingInfo.Scm.DEV_CONNECTION)
            }
        }
    }
}

signing {
    if (getExtraString("signing.keyId") != null) {
        sign(publishing.publications)
    }
}

//https://github.com/gradle/gradle/issues/26132
val signingTasks = tasks.withType<Sign>()
tasks.withType<AbstractPublishToMaven>().configureEach {
    mustRunAfter(signingTasks)
}