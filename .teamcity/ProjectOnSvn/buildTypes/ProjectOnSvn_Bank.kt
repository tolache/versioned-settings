package ProjectOnSvn.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.vstest
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object ProjectOnSvn_Bank : BuildType({
    name = "Bank"

    params {
        param("system.command.line.run.interpreter", "false")
        param("teamcity.svn.native.enabled", "false")
    }

    vcs {
        root(ProjectOnSvn.vcsRoots.ProjectOnSvn_SVNRepository, """+:tags\tag_%env.rev_number% => .""")

        checkoutMode = CheckoutMode.ON_AGENT
        cleanCheckout = true
    }

    steps {
        nuGetInstaller {
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            projects = "Bank.sln"
        }
        visualStudio {
            path = "Bank.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2017
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V15_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V15_0
        }
        vstest {
            vstestPath = "%teamcity.dotnet.vstest.15.0%"
            includeTestFileNames = """BankTest\bin\Debug\BankTest.dll"""
            coverage = dotcover {
            }
        }
    }

    triggers {
        vcs {
            branchFilter = ""
            perCheckinTriggering = true
            enableQueueOptimization = false
        }
    }
})
