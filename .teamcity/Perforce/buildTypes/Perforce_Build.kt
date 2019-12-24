package Perforce.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object Perforce_Build : BuildType({
    name = "Build"

    vcs {
        root(Perforce.vcsRoots.Perforce_QAPerforce)
    }

    steps {
        script {
            scriptContent = """
                echo "Printing file.txt content:"
                cat file.txt
            """.trimIndent()
        }
    }
})
