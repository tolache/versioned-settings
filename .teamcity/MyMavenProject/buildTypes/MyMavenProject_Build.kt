package MyMavenProject.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.Swabra
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.swabra
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven

object MyMavenProject_Build : BuildType({
    name = "Build"

    params {
        param("system.path.macro.MAVEN.REPOSITORY", "https://repo1.maven.org/maven2/")
    }

    vcs {
        root(MyMavenProject.vcsRoots.MyMavenProject_JacocoMavenUnittestv2)
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            coverageEngine = idea {
                includeClasses = "*.*"
            }
        }
    }

    features {
        swabra {
            filesCleanup = Swabra.FilesCleanup.DISABLED
            forceCleanCheckout = true
            lockingProcesses = Swabra.LockingProcessPolicy.REPORT
            verbose = true
            paths = """+:C:\Temp\buildTmp\"""
        }
    }
})
