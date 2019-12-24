package NuGet.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetPack
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetPublish
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object NuGet_HelloSayer : BuildType({
    name = "HelloSayer"

    vcs {
        root(NuGet.vcsRoots.NuGet_HttpsGithubComTolacheHelloSayerRefsHeadsMaster)
    }

    steps {
        nuGetInstaller {
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            projects = "HelloSayer.sln"
            noCache = true
            sources = "http://unit-905.labs.intellij.net:8110/httpAuth/app/nuget/feed/_Root/RootNuGetFeed/v3/index.json"
            updatePackages = updateParams {
            }
        }
        visualStudio {
            path = "HelloSayer.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2019
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V16_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V16_0
        }
        nuGetPack {
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            paths = "HelloSayer/HelloSayer.csproj"
            outputDir = "%teamcity.build.checkoutDir%/HelloSayer/Packages"
            cleanOutputDir = true
        }
        nuGetPublish {
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            packages = """HelloSayer\Packages\*.nupkg"""
            serverUrl = "http://unit-905.labs.intellij.net:8110/httpAuth/app/nuget/feed/_Root/RootNuGetFeed/v3/index.json"
            apiKey = "credentialsJSON:caa1b0e7-c4f2-4ab1-9ce3-468dbc6014fb"
        }
    }

    triggers {
        vcs {
        }
    }
})
