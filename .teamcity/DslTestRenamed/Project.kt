package DslTestRenamed

import DslTestRenamed.buildTypes.*
import DslTestRenamed.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.versionedSettings

object Project : Project({
    uuid = "8bf25bd0-ac48-464e-8c39-593deb5dba51"
    id("DslTestRenamed")
    parentId("_Root")
    name = "DSL Test"

    vcsRoot(DslTest_RepoA)

    buildType(DslTest_BuildA)

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
