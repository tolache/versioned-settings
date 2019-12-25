package PowerShellTest

import PowerShellTest.buildTypes.*
import PowerShellTest.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.azureDevopsConnection

object Project : Project({
    id("PowerShellTest")
    name = "PowerShellTest"

    vcsRoot(PowerShellTest_HttpsGithubComTolachePowerShellTestRefsHeadsMaster)

    buildType(PowerShellTest_Local)
    buildType(PowerShellTest_Build2)
    buildType(PowerShellTest_Build)

    features {
        feature {
            id = "PROJECT_EXT_10"
            type = "buildtype-graphs"
            param("series", """
                [
                  {
                    "type": "valueType",
                    "title": "FreePhysicalMemory",
                    "key": "FreePhysicalMemory"
                  }
                ]
            """.trimIndent())
            param("format", "size")
            param("hideFilters", "")
            param("title", "FreePhysicalMemory")
            param("defaultFilters", "")
            param("seriesTitle", "MySerie")
        }
        feature {
            id = "PROJECT_EXT_12"
            type = "storage_settings"
            param("account-name", "acherenkovartifacts")
            param("storage.name", "Azure")
            param("storage.type", "azure-storage")
            param("secure:account-key", "credentialsJSON:5ec54136-a7df-43c1-aca9-ffbab4f4b142")
        }
        feature {
            id = "PROJECT_EXT_14"
            type = "storage_settings"
            param("secure:aws.secret.access.key", "credentialsJSON:b28b8d2c-0167-46d1-8064-1b7850b1c72d")
            param("aws.external.id", "TeamCity-server-b98e603d-0bd5-4de5-a49c-ac3590e06148")
            param("storage.name", "S3 Storage")
            param("storage.s3.bucket.name", "acherenkov-test")
            param("storage.type", "S3_storage")
            param("aws.access.key.id", "AKIA5JH2VERVPHBFYDM3")
            param("aws.credentials.type", "aws.access.keys")
            param("aws.region.name", "eu-west-3")
            param("storage.s3.upload.presignedUrl.enabled", "true")
            param("storage.s3.upload.numberOfRetries", "2")
        }
        feature {
            id = "PROJECT_EXT_17"
            type = "active_storage"
            param("active.storage.feature.id", "DefaultStorage")
        }
        feature {
            id = "PROJECT_EXT_7"
            type = "CloudImage"
            param("use-spot-instances", "false")
            param("user-tags", "")
            param("agent_pool_id", "-2")
            param("image-instances-limit", "1")
            param("subnet-id", "")
            param("ebs-optimized", "false")
            param("instance-type", "t3.micro")
            param("user-script", "dir")
            param("amazon-id", "ami-04741c47dcd3ed7d5")
            param("source-id", "Amazon Windows Agent")
            param("image-name-prefix", "Amazon Windows Agent")
            param("security-group-ids", "sg-933d5efa,")
            param("profileId", "amazon-3")
        }
        feature {
            id = "PROJECT_EXT_8"
            type = "project-graphs"
            param("series", """
                [
                  {
                    "type": "valueType",
                    "title": "Artifacts Size",
                    "sourceBuildTypeId": "PowerShellTest_Build",
                    "key": "VisibleArtifactsSize"
                  },
                  {
                    "type": "valueType",
                    "title": "Build Artifacts Publishing Time",
                    "sourceBuildTypeId": "PowerShellTest_Build",
                    "key": "buildStageDuration:artifactsPublishing"
                  }
                ]
            """.trimIndent())
            param("format", "text")
            param("title", "New chart")
            param("seriesTitle", "Serie")
        }
        azureDevopsConnection {
            id = "PROJECT_EXT_9"
            displayName = "Azure DevOps"
            serverUrl = "https://dev.azure.com/tolache/"
            accessToken = "credentialsJSON:deecfbf8-21c1-4c39-b285-9e99836b7f79"
        }
        feature {
            id = "amazon-3"
            type = "CloudProfile"
            param("profileServerUrl", "http://unit-905.labs.intellij.net:8110")
            param("secure:access-id", "credentialsJSON:38f8833c-6c80-4522-bcb1-4555494bb1e0")
            param("system.cloud.profile_id", "amazon-3")
            param("total-work-time", "")
            param("description", "")
            param("cloud-code", "amazon")
            param("terminate-after-build", "true")
            param("enabled", "true")
            param("max-running-instances", "1")
            param("agentPushPreset", "")
            param("profileId", "amazon-3")
            param("name", "Amazon")
            param("next-hour", "")
            param("secure:secret-key", "credentialsJSON:260ae35f-b524-4109-acde-c29ef483a5c7")
            param("region", "eu-north-1")
            param("terminate-idle-time", "30")
            param("not-checked", "")
        }
    }
})
