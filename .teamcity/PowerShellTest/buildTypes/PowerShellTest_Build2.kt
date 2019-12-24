package PowerShellTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.PowerShellStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell

object PowerShellTest_Build2 : BuildType({
    name = "Build 2"
    description = "additional build with no VCS root, made to test env. var. output"

    params {
        param("env.TESTVAR", "testvalue")
    }

    steps {
        powerShell {
            edition = PowerShellStep.Edition.Core
            scriptMode = file {
                path = """C:\Temp\script.ps1"""
            }
        }
    }

    dependencies {
        artifacts(PowerShellTest_Build) {
            cleanDestination = true
            artifactRules = "+:TestFile.dat=>."
            enabled = false
        }
    }
})
