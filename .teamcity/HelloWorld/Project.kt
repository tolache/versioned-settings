package HelloWorld

import HelloWorld.buildTypes.*
import HelloWorld.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.nuGetFeed

object Project : Project({
    id("HelloWorld")
    name = "Hello_World"
    defaultTemplate = RelativeId("CheckoutOnAgentTemplate")

    vcsRoot(HelloWorld_HttpsGithubComTolachePowerShellTestRefsHeadsMaster)
    vcsRoot(HelloWorld_HttpsGithubComTolacheHelloSayerRefsHeadsMaster)
    vcsRoot(HelloWorld_HttpsGithubComTolacheHelloWorldRefsHeadsMaster)

    buildType(HelloWorld_HelloSayerBuild)
    buildType(HelloWorld_MSBuild)
    buildType(HelloWorld_Deploy)
    buildType(HelloWorld_Build)

    features {
        nuGetFeed {
            id = "repository-nuget-LocalNuGetFeed"
            name = "LocalNuGetFeed"
            description = "Local NuGet Feed"
        }
    }

    cleanup {
        baseRule {
            artifacts(builds = 1)
            preventDependencyCleanup = false
        }
    }
    buildTypesOrder = arrayListOf(HelloWorld_HelloSayerBuild, HelloWorld_Build)
})
