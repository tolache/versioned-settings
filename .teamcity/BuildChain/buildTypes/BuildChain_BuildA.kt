package BuildChain.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object BuildChain_BuildA : BuildType({
    name = "Build A"

    vcs {
        root(BuildChain.vcsRoots.BuildChain_RepoA)
        root(BuildChain.vcsRoots.BuildChain_RepoB, "+:. => B")
    }

    steps {
        script {
            scriptContent = """
                echo "repoA file.txt content:"
                cat file.txt
                echo "repoB file.txt content:"
                cat B\file.txt
            """.trimIndent()
        }
    }
})
