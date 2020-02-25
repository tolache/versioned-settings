package DslTest_SubDslTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object DslTest_SubDslTest_BuildB : BuildType({
    uuid = "453f19b3-79c5-4b70-bc48-f5bfc17e72da"
    name = "Build B"

    vcs {
        root(DslTest_SubDslTest.vcsRoots.DslTest_SubDslTest_RepoB)
    }

    steps {
        script {
            scriptContent = "cat file.txt"
        }
    }
})
