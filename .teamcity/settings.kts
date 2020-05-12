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

version = "2019.2"

project {

    vcsRoot(RepoD)

    buildType(BuildConfA)
    buildType(BuildConfB)
    buildType(BuildConfC)

    params {
        param("teamcity.ui.settings.readOnly", "false")
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
                echo "Run"                    
            """.trimIndent()
        }
    }

    triggers {
        vcs {
            triggerRules = "+:root=${DslContext.settingsRoot.id}:**"

            branchFilter = "+:PROJECT_X*"
            watchChangesInDependencies = true
        }
    }

    dependencies {
        dependency(BuildConfB) {
            snapshot {
            }
        }
        artifacts(BuildConfC) {
            artifactRules = """
                Installer*.exe
            """.trimIndent()
        }
    }
})

object BuildConfB : BuildType({
    name = "Build Configuration B"
    description = "Build Configuration B"

    artifactRules = """
        %OUTPUT_DIR%/*.exe
    """.trimIndent()

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
            scriptContent = """
                Write-Host "Run"       
            """.trimIndent()
        }
    }
    triggers {
        vcs {
            branchFilter = """
                +:<default>
            """.trimIndent()
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
                artifactRules = """
                    Installer*.exe
                """.trimIndent()
            }
        }
    }
})

object BuildConfC : BuildType({
    name = "Build Configuration C"
    description = "Build Configuration C"

    allowExternalStatus = true
    artifactRules = """
        output\Installer*.exe
    """.trimIndent()

    vcs {
        root(DslContext.settingsRoot)

        cleanCheckout = true
        showDependenciesChanges = true
    }

    steps {
        script {
            name = "Step 1"
            scriptContent = """
                echo "Create Installer.exe" > Installer.exe
            """.trimIndent()
        }
    }

    triggers {
        vcs {
            triggerRules = """
                +:root=${DslContext.settingsRoot.id}:**
            """.trimIndent()

            branchFilter = """
                +:<default>
            """.trimIndent()
        }
    }
})

object RepoD : GitVcsRoot({
    name = "repoD"
    url = "https://github.com/tolache/repoD"
    branchSpec = "+:refs/tags/*"
    authMethod = password {
        userName = "tolache"
        password = "credentialsJSON:7f1be8ea-c51f-420d-8f5a-248d2e2090d0"
    }
})
