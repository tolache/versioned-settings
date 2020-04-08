package DslTest.buildTypes

import DslTest.vcsRoots.DslTest_RepoB
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule

object DslTest_elements_test_rc_branches : BuildType({
    templates(DslTest_elements_test_template)
    id = AbsoluteId("elements_test_rc_branches")
    name = "release candidates: DslTest (any SQL Server)"
    description = "Install and run tests on any SQL Server"

    buildNumberPattern = "${DslTest_BuildB.depParamRefs.buildNumber}"

    params {
        param("BuildZipFileNameTestable", "${DslTest_BuildB.depParamRefs["system.BuildZipFileNameTestable"]}")
        param("system.ElementsDatabaseBackupPath", """C:\TestDatabaseBackups\suzie.db.rc.%teamcity.build.branch%.bak""")
    }

    vcs {
        root(DslTest_RepoB)
    }

    triggers {
        schedule {
            id = "TRIGGER_3"
            schedulingPolicy = daily {
                hour = 20
            }
            triggerRules = "+:root=${DslTest_RepoB.id}:**"

            triggerBuild = always()
        }
    }

    dependencies {
        dependency(DslTest_BuildB) {
            snapshot {
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_5"
                cleanDestination = true
                artifactRules = """
                    %system.BuildZipFileNameTestable% => .
                    integration-tests.msbuild.xml => .
                """.trimIndent()
            }
        }
    }
})