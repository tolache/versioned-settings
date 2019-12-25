package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller

object NuGet_1 : Template({
    id("NuGet")
    name = "NuGet"
    description = "Template for NuGet project"

    steps {
        nuGetInstaller {
            name = "Restore packages"
            id = "RUNNER_24"
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            projects = "Hello_World/Hello_World.sln"
            sources = """
                %env.microsoft.nuget.feed%
                %env.teamcity.nuget.feed%
            """.trimIndent()
        }
    }
})
