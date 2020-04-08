package DslTest

import DslTest.buildTypes.*
import DslTest.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.versionedSettings
import test.buildTypes.DslTest_elements_test_master_2014
import test.buildTypes.DslTest_elements_test_rc_branches

object Project : Project({
    uuid = "8bf25bd0-ac48-464e-8c39-593deb5dba51"
    id("DslTest")
    parentId("_Root")
    name = "DSL Test"

    vcsRoot(DslTest_RepoA)
    vcsRoot(DslTest_RepoB)

    buildType(DslTest_BuildA)
    buildType(DslTest_BuildB)
    buildType(DslTest_elements_test_master_2014)
    buildType(DslTest_elements_test_rc_branches)

    params {
        param("teamcity.ui.settings.readOnly", "false")
    }

    features {
        versionedSettings {
            id = "PROJECT_EXT_16"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
            rootExtId = "VersionedSettings"
            showChanges = false
            settingsFormat = VersionedSettings.Format.KOTLIN
            storeSecureParamsOutsideOfVcs = true
        }
    }
})
