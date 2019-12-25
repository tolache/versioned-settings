package HelloWorld.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MSBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.msBuild
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object HelloWorld_MSBuild : BuildType({
    name = "MSBuild"

    steps {
        msBuild {
            id = "RUNNER_8"
            path = """C:\Users\Anatoly.Cherenkov\source\repos\hello-world\Hello_World\Hello_World.sln"""
            version = MSBuildStep.MSBuildVersion.V14_0
            toolsVersion = MSBuildStep.MSBuildToolsVersion.V14_0
            args = """/p:OutputPath=\Test"""
        }
    }

    triggers {
        vcs {
            id = "vcsTrigger"
        }
    }
})
