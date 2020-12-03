import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.notifications
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dockerCommand

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

    vcsRoot(SettingsRootCopy)
    vcsRoot(RepoD)

    buildType(BuildConfA)
    buildType(BuildConfB)
    buildType(BuildConfC)

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
}

object BuildConfA : BuildType({
    name = "Build Configuration A"
    description = "Build Configuration A"

    params {
        param("teamcity.vcsTrigger.runBuildInNewEmptyBranch", "true")
    }

    vcs {
        cleanCheckout = true
        excludeDefaultBranchChanges = true
        showDependenciesChanges = true
    }

    steps {
        script {
            scriptContent = """
            echo "Settings from: develop"
            """.trimIndent()
        }
        dockerCommand {
            commandType = other {
                subCommand = "login"
            }
        }
    }

    triggers {
        vcs {
            triggerRules = "+:root=${DslContext.settingsRoot.id}:**"

            watchChangesInDependencies = true
        }
    }

    dependencies {
        snapshot(BuildConfB) {
            onDependencyFailure = FailureAction.IGNORE
            onDependencyCancel = FailureAction.CANCEL
        }
        artifacts(BuildConfC) {
            artifactRules = "Installer*.exe"
        }
    }

    features {
        dockerSupport {
            loginToRegistry = on {
                dockerRegistryId = "PROJECT_EXT_20"
            }
        }
        notifications {
            notifierSettings = slackNotifier {
                connection = "PROJECT_EXT_22"
                sendTo = "#unit-905-teamcity-notificatons"
                messageFormat = verboseMessageFormat {
                    addBranch = true
                    addChanges = true
                    addStatusText = true
                    maximumNumberOfChanges = 10
                }
            }
            buildStarted = true
        }
    }
})

object BuildConfB : BuildType({
    name = "Build Configuration B"
    description = "Build Configuration B"

    artifactRules = "%OUTPUT_DIR%/*.exe"

    params {
        text("OUTPUT_DIR", "exported_systems", display = ParameterDisplay.HIDDEN, readOnly = true, allowEmpty = true)
        text("teamcity.buildQueue.allowMerging", "true", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    steps {
        script {
            name = "Step 1"
            scriptContent = """echo "Run"       """
        }
    }

    triggers {
        vcs {
            branchFilter = "+:<default>"
            watchChangesInDependencies = true
        }
    }

    failureConditions {
        executionTimeoutMin = 60
    }

    dependencies {
        dependency(BuildConfC) {
            snapshot {
            }

            artifacts {
                artifactRules = "Installer*.exe"
            }
        }
    }
})

object BuildConfC : BuildType({
    name = "Build Configuration C"
    description = "Build Configuration C"

    allowExternalStatus = true
    artifactRules = """output\Installer*.exe"""
    maxRunningBuilds = 1

    params {
        password("myToken", "credentialsJSON:938a7f8b-8130-4c45-9373-b537839c7116")
    }

    vcs {
        root(RepoD, "-:fileE.txt")

        cleanCheckout = true
        showDependenciesChanges = true
    }

    steps {
        script {
            name = "Step 1"
            scriptContent = """
                mkdir output
                echo "Create Installer.exe" > output/Installer.exe
            """.trimIndent()
        }
                script {
            name = "Sleep"
            scriptContent = """
                echo %myToken% > token.txt
            """.trimIndent()
        }
    }

    triggers {
        vcs {
            triggerRules = "+:root=${DslContext.settingsRoot.id}:**"

            branchFilter = "+:<default>"
        }
    }
})

object RepoD : GitVcsRoot({
    name = "repoD"
    url = "https://github.com/tolache/repoD"
    branchSpec = """
        +:refs/tags/*
        +:refs/heads/*
    """.trimIndent()
    authMethod = password {
        userName = "tolache"
        password = "credentialsJSON:17f19de6-3eb1-4a76-a724-40edd8f3a9e4"
    }
})

object SettingsRootCopy : GitVcsRoot({
    name = "settings-root-copy"
    url = "git@github.com:tolache/versioned-settings.git"
    branchSpec = "refs/heads/*"
    authMethod = uploadedKey {
        userName = "git"
        uploadedKey = "github.pem"
    }
})
