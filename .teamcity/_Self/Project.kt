package _Self

import _Self.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.azureDevopsConnection
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.bitbucketCloudConnection
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.buildReportTab
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubConnection
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.gitlabEEConnection
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.nuGetFeed

object Project : Project({
    description = "Contains all other projects"

    template(MaximumNumberOfBuilds)
    template(CheckoutOnAgentTemplate)
    template(NuGet_1)

    params {
        param("azure.devops.token", "yyy6x3di65rndxr3jz4fvsq5dm6of736wbjexra2ujaokfkhqjrq")
    }

    features {
        buildReportTab {
            id = "PROJECT_EXT_1"
            title = "Code Coverage"
            startPage = "coverage.zip!index.html"
        }
        gitlabEEConnection {
            id = "PROJECT_EXT_11"
            displayName = "QA GitLab"
            serverUrl = "http://tcqa-gitlab.labs.intellij.net/"
            applicationId = "8da7bc0b2e8f83ad7cb867a9b240740ee9528df6820e324e2222d644712eaa07"
            clientSecret = "credentialsJSON:f5c8ffe7-ee26-4a4c-aaec-500de39f9358"
        }
        azureDevopsConnection {
            id = "PROJECT_EXT_13"
            displayName = "Azure DevOps"
            serverUrl = "https://dev.azure.com/tolache/"
            accessToken = "credentialsJSON:ec7e5900-df80-49a7-b537-58b1e6126ff3"
        }
        githubConnection {
            id = "PROJECT_EXT_2"
            displayName = "GitHub.com"
            clientId = "4dc6d9c981ccc60ce1e7"
            clientSecret = "credentialsJSON:19f564cd-8d77-4ba7-b1d8-2588f6232b88"
        }
        bitbucketCloudConnection {
            id = "PROJECT_EXT_3"
            displayName = "Bitbucket Cloud"
            key = "hstAFKDfa7FPYt9jvE"
            clientSecret = "credentialsJSON:11d7a03f-05ec-4664-8086-fa68222a4021"
        }
        nuGetFeed {
            id = "repository-nuget-RootNuGetFeed"
            name = "RootNuGetFeed"
            description = "Root project NuGet Feed"
        }
    }

    cleanup {
        baseRule {
            preventDependencyCleanup = false
        }
    }

    subProject(TfvcTestProject.Project)
    subProject(Perforce.Project)
    subProject(BuildChain.Project)
    subProject(MyMavenProject.Project)
    subProject(Sample.Project)
    subProject(QaGitLab.Project)
    subProject(SlnPlacementTest.Project)
    subProject(NuGet.Project)
    subProject(PizzaEvaluator.Project)
    subProject(HelloWorld.Project)
    subProject(ProjectOnSvn.Project)
    subProject(Idea.Project)
    subProject(TestbedAntSample.Project)
    subProject(teamcity_nunit_samples.Project)
    subProject(PowerShellTest.Project)
})
