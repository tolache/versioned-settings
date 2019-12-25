package HelloWorld.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.exec

object HelloWorld_Deploy : BuildType({
    name = "Deploy"

    vcs {
        cleanCheckout = true
    }

    steps {
        exec {
            name = "Copy file"
            id = "RUNNER_1"
            path = "cp"
            arguments = """C:\Temp\ C:\Deploy\ -r"""
        }
    }

    dependencies {
        dependency(HelloWorld_Build) {
            snapshot {
                reuseBuilds = ReuseBuilds.NO
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_1"
                artifactRules = """**\HelloSayer*.nupkg => C:\Temp"""
            }
        }
    }
})
