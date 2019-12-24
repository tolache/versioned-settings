package Sample.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dotnetTest
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object Sample_Build : BuildType({
    name = "Build"

    vcs {
        root(Sample.vcsRoots.Sample_HttpsGithubComTolacheSampleNUnitTestProjectRefsHeadsMaster)
    }

    steps {
        nuGetInstaller {
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            projects = "Sample.NUnitTestProject.sln"
        }
        visualStudio {
            path = "Sample.NUnitTestProject.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2019
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V16_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V16_0
        }
        dotnetTest {
            projects = "Sample.NUnitTestProject/Sample.NUnitTestProject.csproj"
        }
    }

    triggers {
        vcs {
        }
    }
})
