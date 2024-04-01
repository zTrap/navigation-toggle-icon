object PublishingInfo {
    const val BASE_ARTIFACT_NAME = "navigation-toggle-icon"
    const val BASE_REPO_BUCKET = "zTrap/$BASE_ARTIFACT_NAME"
    const val BASE_REPO_URL = "https://github.com/$BASE_REPO_BUCKET"

    const val GROUP = "ru.ztrap"

    object Repo {
        const val URL = BASE_REPO_URL
    }

    object Scm {
        const val URL = BASE_REPO_URL
        const val CONNECTION = "scm:git@github.com:${BASE_REPO_BUCKET}.git"
        const val DEV_CONNECTION = "scm:git@github.com:${BASE_REPO_BUCKET}.git"
    }

    object License {
        const val NAME = "Apache-2.0 license"
        const val URL = "https://www.apache.org/licenses/LICENSE-2.0"
        const val DIST = "repo"
    }

    object Developer {
        const val ID = "zTrap"
        const val NAME = "Peter Gulko"
        const val EMAIL = "ztrap.developer@gmail.com"
    }

    object Artifact {
        const val ID = BASE_ARTIFACT_NAME
        const val NAME = "Compose Multiplatform navigation toggle icon"
        const val DESCRIPTION = "Compose Multiplatform icon which have 3 states (burger, arrow and cross) and animated transitions between them"
        const val VERSION = "1.0.0"
    }
}