package SlnPlacementTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object SlnPlacementTest_Build : BuildType({
    name = "Build"

    params {
        param("sln.path", "%sln.path%")
    }

    vcs {
        root(SlnPlacementTest.vcsRoots.SlnPlacementTest_HttpsGithubComTolacheSlnPlacementTestRefsHeadsMaster)
    }

    steps {
        powerShell {
            scriptMode = script {
                content = """
                    ${'$'}BuildBranch = "%teamcity.build.branch%"
                    ${'$'}SNLPath = ""
                    
                    if (${'$'}BuildBranch -eq "version-2.0") {
                      ${'$'}SNLPath = "SLN Placement Test.sln"
                    }
                    else {
                      ${'$'}SNLPath = "SLN Placement Test\SLN Placement Test.sln"
                    }
                    echo "##teamcity[setParameter name='sln.path' value='${'$'}SNLPath']"
                """.trimIndent()
            }
        }
        nuGetInstaller {
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            projects = "%sln.path%"
        }
        visualStudio {
            path = "%sln.path%"
            version = VisualStudioStep.VisualStudioVersion.vs2019
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V16_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V16_0
        }
    }

    triggers {
        vcs {
        }
    }
})
