package QaGitLab.buildTypes

import QaGitLab.vcsRoots.QaGitLab_HttpTcqaGitlabLabsIntellijNetJetbrainsAcherenkovTestGitRefsHeadsMaster
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.vstest
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object QaGitLab_Bank : BuildType({
    name = "Bank"

    vcs {
        root(QaGitLab.vcsRoots.QaGitLab_HttpTcqaGitlabLabsIntellijNetJetbrainsAcherenkovTestGitRefsHeadsMaster, "-:dir1/dir2/PowerShellTest/")

        cleanCheckout = true
        excludeDefaultBranchChanges = true
        showDependenciesChanges = true
    }

    steps {
        nuGetInstaller {
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            projects = "dir1/dir2/Bank/Bank.sln"
        }
        visualStudio {
            path = "dir1/dir2/Bank/Bank.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2019
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V16_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V16_0
        }
        vstest {
            vstestPath = "%teamcity.dotnet.vstest.16.0%"
            includeTestFileNames = """dir1\dir2\Bank\BankTest\bin\Debug\BankTest.dll"""
        }
    }

    triggers {
        vcs {
            triggerRules = "-:dir1/dir2/PowerShellTest/**"
        }
    }

    features {
        pullRequests {
            vcsRootExtId = "${QaGitLab_HttpTcqaGitlabLabsIntellijNetJetbrainsAcherenkovTestGitRefsHeadsMaster.id}"
            provider = gitlab {
                authType = token {
                    token = "credentialsJSON:c1d1b5a2-7ed7-4e15-9741-a8ad66d89e61"
                }
            }
        }
    }
})
