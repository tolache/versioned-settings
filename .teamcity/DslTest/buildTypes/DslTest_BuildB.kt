package DslTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object DslTest_BuildB : BuildType({
    uuid = "c9aad864-8a28-4a9b-a91b-a2880d1e5e0c"
    name = "Build B"

    vcs {
        root(DslTest.vcsRoots.DslTest_RepoB)
    }

    steps {
        script {
            scriptContent = """
                echo "file.txt content:"
                cat file.txt
            """.trimIndent()
        }
    }
})
