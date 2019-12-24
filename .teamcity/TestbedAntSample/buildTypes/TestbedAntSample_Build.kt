package TestbedAntSample.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.Swabra
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.swabra
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.ant
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object TestbedAntSample_Build : BuildType({
    name = "Build"

    vcs {
        root(TestbedAntSample.vcsRoots.TestbedAntSample_HttpsGithubComTolacheTestbedAntSampleRefsHeadsMaster)
    }

    steps {
        ant {
            mode = antFile {
            }
            targets = "all"
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        swabra {
            filesCleanup = Swabra.FilesCleanup.AFTER_BUILD
            lockingProcesses = Swabra.LockingProcessPolicy.REPORT
            verbose = true
            paths = """+:C:\TeamCity\buildAgent\temp\buildTmp"""
        }
    }
})
