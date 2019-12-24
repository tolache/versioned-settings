package HelloWorld.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.nuGetFeedCredentials
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetPack
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetPublish
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object HelloWorld_HelloSayerBuild : BuildType({
    name = "HelloSayer Build"

    vcs {
        root(HelloWorld.vcsRoots.HelloWorld_HttpsGithubComTolacheHelloSayerRefsHeadsMaster)

        cleanCheckout = true
    }

    steps {
        nuGetInstaller {
            id = "RUNNER_1"
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            projects = "HelloSayer.sln"
            updatePackages = updateParams {
            }
        }
        visualStudio {
            id = "RUNNER_2"
            path = "HelloSayer.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2017
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V15_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V15_0
        }
        nuGetPack {
            id = "RUNNER_3"
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            paths = "HelloSayer/HelloSayer.csproj"
            outputDir = "%teamcity.build.checkoutDir%/HelloSayer/Packages"
            cleanOutputDir = true
        }
        nuGetPublish {
            id = "RUNNER_4"
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            packages = """HelloSayer\Packages\*.nupkg"""
            serverUrl = "http://unit-905.labs.intellij.net:8110/httpAuth/app/nuget/feed/HelloWorld/LocalNuGetFeed/v2?replace=true"
            apiKey = "credentialsJSON:caa1b0e7-c4f2-4ab1-9ce3-468dbc6014fb"
        }
    }

    triggers {
        vcs {
            id = "TRIGGER_1"
        }
    }

    features {
        nuGetFeedCredentials {
            id = "BUILD_EXT_3"
            feedUrl = "https://tolache.pkgs.visualstudio.com/_packaging/HelloSayer_Feed/nuget/v3/index.json"
            username = "unit-905"
            password = "credentialsJSON:f86ac8cb-9cf2-487a-a298-94ad9b444059"
        }
    }
})
