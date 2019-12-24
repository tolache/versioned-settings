package QaGitLab.buildTypes

import QaGitLab.vcsRoots.QaGitLab_HttpTcqaGitlabLabsIntellijNetJetbrainsAcherenkovTestGitRefsHeadsMaster
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object QaGitLab_PowerShell : BuildType({
    name = "PowerShell"

    artifactRules = "TestFile.dat"

    vcs {
        root(QaGitLab.vcsRoots.QaGitLab_HttpTcqaGitlabLabsIntellijNetJetbrainsAcherenkovTestGitRefsHeadsMaster, "-:dir1/dir2/Bank/")
    }

    steps {
        powerShell {
            scriptMode = file {
                path = "dir1/dir2/PowerShellTest/TestScript.ps1"
            }
        }
    }

    triggers {
        vcs {
            triggerRules = "-:dir1/dir2/Bank/**"
        }
    }

    features {
        pullRequests {
            vcsRootExtId = "${QaGitLab_HttpTcqaGitlabLabsIntellijNetJetbrainsAcherenkovTestGitRefsHeadsMaster.id}"
            provider = gitlab {
                authType = token {
                    token = "credentialsJSON:e1aa9c49-dfcb-438b-8bce-a91aef02fb64"
                }
            }
        }
    }
})
