package Idea.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ideaInspections
import jetbrains.buildServer.configs.kotlin.v2019_2.ideaRunner
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object Idea_TestBuildInspections : BuildType({
    name = "Test Build Inspections"

    params {
        param("system.path.macro.MAVEN.REPOSITORY", "https://repo1.maven.org/maven2/")
    }

    vcs {
        root(Idea.vcsRoots.Idea_JavaSample)
    }

    steps {
        ideaRunner {
            pathToProject = ""
            jdk {
                name = "1.8"
                path = "%env.JDK_18%"
                patterns("jre/lib/*.jar", "jre/lib/ext/jfxrt.jar")
                extAnnotationPatterns("%teamcity.tool.idea%/lib/jdkAnnotations.jar")
            }
            pathvars {
                variable("MAVEN_REPOSITORY", "%system.path.macro.MAVEN.REPOSITORY%")
            }
            jvmArgs = "-Xmx256m"
        }
        ideaInspections {
            pathToProject = ""
            jdk {
                name = "1.8"
                path = "%env.JDK_18%"
                patterns("jre/lib/*.jar", "jre/lib/ext/jfxrt.jar")
                extAnnotationPatterns("%teamcity.tool.idea%/lib/jdkAnnotations.jar")
            }
            pathvars {
                variable("MAVEN_REPOSITORY", "%system.path.macro.MAVEN.REPOSITORY%")
            }
            jvmArgs = "-Xmx512m -XX:ReservedCodeCacheSize=240m"
            targetJdkHome = "%env.JDK_18%"
        }
    }

    triggers {
        vcs {
            enabled = false
        }
    }
})
