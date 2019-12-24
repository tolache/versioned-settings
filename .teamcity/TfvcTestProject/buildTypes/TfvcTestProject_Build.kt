package TfvcTestProject.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object TfvcTestProject_Build : BuildType({
    name = "Build"

    vcs {
        root(TfvcTestProject.vcsRoots.TfvcTestProject_TfsHttpsDevAzureComTolacheTfvcTestProject)
    }

    steps {
        powerShell {
            scriptMode = file {
                path = "TestScript.ps1"
            }
            noProfile = false
        }
    }

    triggers {
        vcs {
        }
    }
})
