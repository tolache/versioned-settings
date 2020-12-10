import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.notifications
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.BuildFailureOnMetric
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.failOnMetricChange

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

    buildType(BuildConfA)
    buildType(BuildConfB)

    template(MyBaseTemplate)

    params {
        param("MyMetricThreshold", "%MyMetricThreshold%")
        param("teamcity.ui.settings.readOnly", "true")
        text("SleepDuration", "%SleepDuration%", display = ParameterDisplay.PROMPT, allowEmpty = false)
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
    templates(MyBaseTemplate)
    name = "Build Configuration A"
    description = "Build Configuration A"

    params {
        param("MyMetricThreshold", "60")
    }

    vcs {
        cleanCheckout = true
        excludeDefaultBranchChanges = true
        showDependenciesChanges = true
    }

    steps {
        script {
            id = "RUNNER_1"
            scriptContent = "sleep %SleepDuration%"
        }
    }

    features {
        notifications {
            id = "BUILD_EXT_1"
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
    templates(MyBaseTemplate)
    name = "Build Configuration B"
    description = "Build Configuration B"

    params {
        param("MyMetricThreshold", "30")
    }

    vcs {
        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    steps {
        script {
            name = "Step 1"
            id = "RUNNER_1"
            scriptContent = "Sleep %SleepDuration%"
        }
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})

object MyBaseTemplate : Template({
    name = "MyBaseTemplate"
    description = "My Template for something"

    failureConditions {
        failOnMetricChange {
            id = "someFailureOnMetricChange"
            metric = BuildFailureOnMetric.MetricType.BUILD_DURATION
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.DIFF
            compareTo = build {
                buildRule = lastSuccessful()
            }
            param("metricThreshold", "%MyMetricThreshold%")
        }
    }
})
