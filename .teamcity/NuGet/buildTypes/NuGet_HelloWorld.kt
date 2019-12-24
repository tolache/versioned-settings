package NuGet.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object NuGet_HelloWorld : BuildType({
    name = "HelloWorld"

    vcs {
        root(NuGet.vcsRoots.NuGet_HttpsGithubComTolacheHelloWorldRefsHeadsMaster)
    }

    steps {
        nuGetInstaller {
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            projects = "Hello_World/Hello_World.sln"
            sources = "http://unit-905.labs.intellij.net:8110/httpAuth/app/nuget/feed/_Root/RootNuGetFeed/v3/index.json"
        }
        visualStudio {
            path = "Hello_World/Hello_World.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2019
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V16_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V16_0
        }
    }

    triggers {
        vcs {
        }
    }
})
