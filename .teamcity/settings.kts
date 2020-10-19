import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
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

version = "2020.1"

project {

    vcsRoot(SettingsRootCopy)
    vcsRoot(RepoD)

    buildType(BuildConfA)
    buildType(BuildConfB)
    buildType(BuildConfC)

    params {
        param("teamcity.ui.settings.readOnly", "true")
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
            name = "Step 1"
            scriptContent = """
                echo "Configuration change 3"
                echo "Run"
            """.trimIndent()
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

    vcs {
        root(RepoD, "${DslContext.getParameter("masterCheckoutRules")}")

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
            name = "Step 2"
            scriptContent = """echo "This is the second step.""""
        }
        script {
            name = "Step 3"
            scriptContent = """echo "This is the thrid step.""""
        }
        script {
            name = "Step 4"
            scriptContent = """echo "This is the fourth step.""""
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
