package HelloWorld.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.nuGetFeedCredentials
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.NuGetInstallerStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object HelloWorld_Build : BuildType({
    name = "Hello_World Build"

    artifactRules = """
        Hello_World\Hello_World\bin\Debug\Hello_World.exe
        Hello_World\Hello_World\bin\Debug\Hello_World.pdb
        Hello_World\Hello_World\bin\Debug\HelloSayer.dll
        Hello_World\packages\HelloSayer*\HelloSayer*.nupkg
    """.trimIndent()

    vcs {
        root(HelloWorld.vcsRoots.HelloWorld_HttpsGithubComTolacheHelloWorldRefsHeadsMaster)

        cleanCheckout = true
    }

    steps {
        nuGetInstaller {
            id = "RUNNER_1"
            toolPath = "%teamcity.tool.NuGet.CommandLine.5.1.0%"
            projects = "Hello_World/Hello_World.sln"
            noCache = true
            sources = "http://unit-905.labs.intellij.net:8110/httpAuth/app/nuget/feed/HelloWorld/LocalNuGetFeed/v2"
            updatePackages = updateParams {
                mode = NuGetInstallerStep.UpdateMode.PackagesConfig
            }
        }
        visualStudio {
            name = "Hello_World"
            id = "RUNNER_2"
            path = "Hello_World/Hello_World.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2019
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V16_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V16_0
        }
    }

    triggers {
        vcs {
            id = "TRIGGER_1"
        }
        trigger {
            id = "TRIGGER_2"
            type = "nuget.simple"
            param("nuget.include.prerelease", "true")
            param("nuget.source", "http://UNIT-905.Labs.IntelliJ.Net:8110/httpAuth/app/nuget/feed/HelloWorld/LocalNuGetFeed/v2")
            param("nuget.exe", "%teamcity.tool.NuGet.CommandLine.DEFAULT%")
            param("nuget.username", "admin")
            param("secure:nuget.password", "credentialsJSON:eca3d0a1-0303-4a95-bc66-f386c7b79571")
            param("nuget.package", "HelloSayer")
        }
    }

    features {
        nuGetFeedCredentials {
            id = "BUILD_EXT_4"
            feedUrl = "http://unit-905.labs.intellij.net:8110/httpAuth/app/nuget/feed/HelloWorld/LocalNuGetFeed/v2"
            username = "admin"
            password = "credentialsJSON:eca3d0a1-0303-4a95-bc66-f386c7b79571"
        }
    }
})
