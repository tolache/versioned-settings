package BuildChain.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object BuildChain_BuildB : BuildType({
    name = "Build B"

    vcs {
        root(BuildChain.vcsRoots.BuildChain_RepoB)
    }

    steps {
        script {
            scriptContent = """
                echo "file.txt content:"
                cat file.txt
                echo ${BuildChain_BuildA.depParamRefs["build.vcs.number"]}
            """.trimIndent()
        }
    }

    dependencies {
        snapshot(BuildChain_BuildA) {
        }
    }
})
