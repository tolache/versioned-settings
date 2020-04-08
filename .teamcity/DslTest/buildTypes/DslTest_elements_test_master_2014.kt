package test.buildTypes

import DslTest.buildTypes.DslTest_BuildA
import DslTest.vcsRoots.DslTest_RepoA
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule

object DslTest_elements_test_master_2014 : BuildType({
    templates(DslTest_elements_test_template)
    id = AbsoluteId("elements_test_master_2014")
    name = "master 2014: test (SQL Server 2014)"
    description = "Install and run tests on SQL Server 2014"

    buildNumberPattern = "${DslTest_BuildA.depParamRefs.buildNumber}"

    params {
        param("BuildZipFileNameTestable", "${DslTest_BuildA.depParamRefs["system.BuildZipFileNameTestable"]}")
    }

    vcs {
        root(DslTest_RepoA)
    }

    triggers {
        schedule {
            id = "TRIGGER_3"
            schedulingPolicy = daily {
                hour = 20
            }
            triggerRules = "+:root=${DslTest_RepoA.id}:**"

            triggerBuild = always()
        }
    }

    dependencies {
        dependency(DslTest_BuildA) {
            snapshot {
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_8"
                cleanDestination = true
                artifactRules = """
                    %system.BuildZipFileNameTestable% => .
                    integration-tests.msbuild.xml => .
                """.trimIndent()
            }
        }
    }

    requirements {
        equals("system.SqlServerVersion", "2014", "RQ_4")
    }
})