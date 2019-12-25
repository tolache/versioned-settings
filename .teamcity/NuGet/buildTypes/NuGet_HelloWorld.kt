package NuGet.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object NuGet_HelloWorld : BuildType({
    templates(_Self.buildTypes.NuGet_1)
    name = "HelloWorld"

    params {
        param("teamcity.nuget.feed.guestAuth._Root.default.v2", "")
        param("env.microsoft.nuget.feed", "")
    }

    vcs {
        root(NuGet.vcsRoots.NuGet_HttpsGithubComTolacheHelloWorldRefsHeadsMaster)
    }

    steps {
        visualStudio {
            id = "RUNNER_33"
            path = "Hello_World/Hello_World.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2019
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V16_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V16_0
        }
    }

    triggers {
        vcs {
            id = "vcsTrigger"
        }
    }
})
