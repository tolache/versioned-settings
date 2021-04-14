import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2020.2"

project {

    vcsRoot(VersionedSettingsCopy)

    buildType(Build01)
    buildType(Build02)
    buildType(Build03)
    buildType(Package01)
    buildType(Package02)
    buildType(Overview01)

    params {
        param("teamcity.ui.settings.readOnly", "false")
    }

    features {
        feature {
            id = "PROJECT_EXT_18"
            type = "CloudImage"
            param("profileId", "VRDC-1")
            param("agent_pool_id", "-2")
            param("source-id", "dummy_docker_agent")
        }
        feature {
            id = "VRDC-1"
            type = "CloudProfile"
            param("run.var.teamcity.docker.cloud.daemon_info", "")
            param("run.var.teamcity.docker.cloud.server_url", "")
            param("profileServerUrl", "http://172.30.73.182:8110/")
            param("run.var.teamcity.docker.cloud.client_uuid", "5e40a58f-9f2e-4306-99d0-6f4cafba3db4")
            param("system.cloud.profile_id", "VRDC-1")
            param("total-work-time", "")
            param("description", "")
            param("cloud-code", "VRDC")
            param("enabled", "true")
            param("agentPushPreset", "")
            param("run.var.teamcity.docker.cloud.instance_uri", "npipe:////./pipe/docker_engine")
            param("profileId", "VRDC-1")
            param("name", "Dummy Docker Profile")
            param("next-hour", "")
            param("run.var.teamcity.docker.cloud.tested_image", "")
            param("run.var.teamcity.docker.cloud.use_default_win_named_pipe", "true")
            param("run.var.teamcity.docker.cloud.img_param", """
                [{
                        "Administration": {
                            "Version": 4,
                            "RmOnExit": false,
                            "PullOnCreate": true,
                            "MaxInstanceCount": 5,
                            "UseOfficialTCAgentImage": true,
                            "Profile": "dummy_docker_agent"
                        },
                        "Container": {
                            "HostConfig": {
                                "OomKillDisable": false,
                                "Privileged": false
                            }
                        },
                        "Editor": {
                            "MemoryUnit": "bytes",
                            "MemorySwapUnit": "bytes"
                        }
                    }
                ]
            """.trimIndent())
            param("terminate-idle-time", "10")
        }
    }

    cleanup {
        baseRule {
            history(builds = 1000, days = 90)
            artifacts(builds = 7, days = 2, artifactPatterns = """
                +:**/*
                -:uploaded-files
                -:release-prep-reports/
                -:release-reports/
                -:s3-upload-lists/
            """.trimIndent())
            preventDependencyCleanup = false
        }
    }
}

object Build01 : BuildType({
    name = "Build01"

    vcs {
        root(DslContext.settingsRoot, "-:.teamcity")
    }

    steps {
        powerShell {
            scriptMode = script {
                content = "Start-Sleep -s 10"
            }
        }
    }
})

object Build02 : BuildType({
    name = "Build02"

    steps {
        powerShell {
            scriptMode = script {
                content = """
                    Start-Sleep -s 3
                    Exit 0
                """.trimIndent()
            }
        }
    }
})

object Build03 : BuildType({
    name = "Build03"

    steps {
        powerShell {
            scriptMode = script {
                content = "Start-Sleep -s 10"
            }
        }
    }
})

object Package01 : BuildType({
    name = "Package01"

    dependencies {
        snapshot(Build01) {
            reuseBuilds = ReuseBuilds.SUCCESSFUL
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
        snapshot(Build02) {
            reuseBuilds = ReuseBuilds.SUCCESSFUL
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})

object Package02 : BuildType({
    name = "Package02"

    dependencies {
        snapshot(Build02) {
            reuseBuilds = ReuseBuilds.SUCCESSFUL
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
        snapshot(Build03) {
            reuseBuilds = ReuseBuilds.SUCCESSFUL
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})

object Overview01 : BuildType({
    name = "Overview01"

    type = Type.COMPOSITE

    vcs {
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(Package01) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
        snapshot(Package02) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})

object VersionedSettingsCopy : GitVcsRoot({
    name = "versioned-settings-copy"
    url = "https://github.com/tolache/versioned-settings"
    branch = "refs/heads/master"
    authMethod = password {
        userName = "tolache"
        password = "credentialsJSON:7d8cca8e-bc35-4156-a965-0b32123691bc"
    }
})
