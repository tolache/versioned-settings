package test.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MSBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.NUnitStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.msBuild
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nunit
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule

object elements_test_template : Template({
    uuid = "21ab50ef-b97a-4cfc-898e-be9f7e44a1ca"
    name = "Elements Test Template"

    params {
        param("system.ZippedBuildFilePath", "%BuildZipFileNameTestable%")
    }

    vcs {
        checkoutMode = CheckoutMode.MANUAL
    }

    steps {
        msBuild {
            name = "Install"
            id = "RUNNER_32"
            path = "integration-tests.msbuild.xml"
            toolsVersion = MSBuildStep.MSBuildToolsVersion.V16_0
            targets = "Install"
        }
        nunit {
            name = "Unit test"
            id = "RUNNER_33"
            nunitVersion = NUnitStep.NUnitVersion.NUnit_2_6_3
            platform = NUnitStep.Platform.x86
            runtimeVersion = NUnitStep.RuntimeVersion.v4_0
            includeTests = """
                %system.InstallPath%\UnitTests\UnitTests.dll
                %system.InstallPath%\UnitTests\Symplectic.UnitTests.dll
            """.trimIndent()
            excludeCategories = "Integration"
        }
        nunit {
            name = "NUnit Integration tests"
            id = "RUNNER_34"
            nunitVersion = NUnitStep.NUnitVersion.NUnit_2_6_3
            platform = NUnitStep.Platform.x86
            runtimeVersion = NUnitStep.RuntimeVersion.v4_0
            includeTests = """
                %system.InstallPath%\UnitTests\UnitTests.dll
                %system.InstallPath%\UnitTests\Symplectic.UnitTests.dll
            """.trimIndent()
            includeCategories = "Integration"
        }
        msBuild {
            name = "Test"
            id = "RUNNER_35"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            path = "integration-tests.msbuild.xml"
            toolsVersion = MSBuildStep.MSBuildToolsVersion.V16_0
            targets = "Test"
        }
    }

    triggers {
        schedule {
            id = "TRIGGER_3"
            schedulingPolicy = daily {
                hour = 20
            }
            triggerBuild = always()
            withPendingChangesOnly = false
        }
    }

    features {
        commitStatusPublisher {
            id = "BUILD_EXT_4"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:XXX-XXX-XXX-XXX-XXX"
                }
            }
        }
    }

    requirements {
        moreThan("system.SqlServerVersion", "2008", "RQ_3")
    }
})