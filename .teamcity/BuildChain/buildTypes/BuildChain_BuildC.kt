package BuildChain.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object BuildChain_BuildC : BuildType({
    name = "Build C"

    vcs {
        root(BuildChain.vcsRoots.BuildChain_RepoC)
    }

    steps {
        script {
            scriptContent = """
                echo "file.txt content:"
                cat file.txt
            """.trimIndent()
        }
    }

    dependencies {
        snapshot(BuildChain_BuildB) {
        }
    }
})
