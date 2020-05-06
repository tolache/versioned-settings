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

    buildType(BuildA)
    buildType(BuildB)

    params {
        param("teamcity.vcsTrigger.runBuildInNewEmptyBranch", "true")
        param("teamcity.ui.settings.readOnly", "false")
    }
}

object BuildA : BuildType({
    name = "Build A"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            scriptContent = """
                echo "Config version 2"
                echo "Printing file* content in branch: master"
                cat src/file*
            """.trimIndent()
        }
    }

    triggers {
        vcs {
            triggerRules = "+:src/**"
        }
    }
})

object BuildB : BuildType({
    name = "Build B"

    steps {
        script {
            scriptContent = "echo OK!"
        }
    }

    triggers {
        vcs {
            branchFilter = ""
            watchChangesInDependencies = true
        }
    }

    dependencies {
        snapshot(BuildA) {
        }
    }
})

object RepoD : GitVcsRoot({
    name = "repoD"
    url = "https://github.com/tolache/repoD"
    branchSpec = "+:refs/heads/*"
    authMethod = password {
        userName = "tolache"
        password = "credentialsJSON:7f1be8ea-c51f-420d-8f5a-248d2e2090d0"
    }
})
