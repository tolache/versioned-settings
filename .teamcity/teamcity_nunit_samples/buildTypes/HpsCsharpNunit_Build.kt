package teamcity_nunit_samples.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nunit
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object HpsCsharpNunit_Build : BuildType({
    name = "Build"

    artifactRules = """
        %system.teamcity.build.tempDir%\..\agentTmp\*.nunit
        %system.teamcity.build.tempDir%\..\agentTmp\*.dotCover
    """.trimIndent()

    vcs {
        root(teamcity_nunit_samples.vcsRoots.teamcity_nunit_samples_1)
    }

    steps {
        nuGetInstaller {
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            projects = "teamcity-nunit-samples.sln"
        }
        visualStudio {
            path = "teamcity-nunit-samples.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2019
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V16_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V16_0
        }
        nunit {
            nunitPath = "%teamcity.tool.NUnit.Console.DEFAULT%"
            includeTests = """Tests\bin\Debug\Tests.dll"""
            coverage = dotcover {
            }
        }
    }

    triggers {
        vcs {
        }
    }
})
