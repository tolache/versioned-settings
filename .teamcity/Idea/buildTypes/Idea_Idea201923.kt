package Idea.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ideaInspections
import jetbrains.buildServer.configs.kotlin.v2019_2.ideaRunner
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object Idea_Idea201923 : BuildType({
    name = "IDEA 2019.2.3"

    vcs {
        root(Idea.vcsRoots.Idea_HttpsGithubComTolacheTw62337refsHeadsMaster)
    }

    steps {
        ideaRunner {
            pathToProject = "InspectionsTest.ipr"
            jdk {
                name = "1.8"
                path = "%env.JDK_18%"
                patterns("jre/lib/*.jar", "jre/lib/ext/jfxrt.jar")
                extAnnotationPatterns("%teamcity.tool.idea%/lib/jdkAnnotations.jar")
            }
            jvmArgs = "-Xmx256m"
        }
        ideaInspections {
            pathToProject = "InspectionsTest.ipr"
            jdk {
                name = "1.8"
                path = "%env.JDK_18%"
                patterns("jre/lib/*.jar", "jre/lib/ext/jfxrt.jar")
                extAnnotationPatterns("%teamcity.tool.idea%/lib/jdkAnnotations.jar")
            }
            jvmArgs = "-Xmx512m -XX:ReservedCodeCacheSize=240m"
            targetJdkHome = "%env.JDK_18%"
            profileName = "UZ Default"
            profilePath = "UZ-inspections-cia.xml"
        }
    }

    triggers {
        vcs {
            enabled = false
        }
    }
})
