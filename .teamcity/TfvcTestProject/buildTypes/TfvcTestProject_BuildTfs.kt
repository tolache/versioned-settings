package TfvcTestProject.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.vstest
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object TfvcTestProject_BuildTfs : BuildType({
    name = "Build TFS"

    vcs {
        root(TfvcTestProject.vcsRoots.TfvcTestProject_TcqaTfs2018)

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
        }
    }

    triggers {
        vcs {
            branchFilter = ""
        }
    }
})
