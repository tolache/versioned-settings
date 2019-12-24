package PowerShellTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object PowerShellTest_Build : BuildType({
    name = "Build"

    artifactRules = """
        TestScript.ps1
        TestFile.dat
        TestFile.zip
    """.trimIndent()

    params {
        param("env.TESTVAR", """C:\Temp\""")
    }

    vcs {
        root(PowerShellTest.vcsRoots.PowerShellTest_HttpsGithubComTolachePowerShellTestRefsHeadsMaster)

        cleanCheckout = true
    }

    steps {
        powerShell {
            scriptMode = file {
                path = "TestScript.ps1"
            }
            noProfile = false
        }
        step {
            type = "ssh-exec-runner"
            param("jetbrains.buildServer.deployer.username", "jetbrains")
            param("jetbrains.buildServer.sshexec.command", "pwd")
            param("jetbrains.buildServer.deployer.targetUrl", "tola-ubuntu.Labs.IntelliJ.Net")
            param("secure:jetbrains.buildServer.deployer.password", "credentialsJSON:7222dd1c-cab0-4096-a661-0db93959eb42")
            param("jetbrains.buildServer.sshexec.authMethod", "PWD")
        }
    }

    triggers {
        vcs {
        }
    }

    failureConditions {
        errorMessage = true
    }

    cleanup {
        baseRule {
            artifacts(builds = 2)
        }
    }
})
