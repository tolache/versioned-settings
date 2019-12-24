package Idea.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object Idea_HttpsGithubComTolacheTestPrRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/tolache/TestPr#refs/heads/master"
    url = "https://github.com/tolache/TestPr"
    authMethod = password {
        userName = "tolache"
        password = "credentialsJSON:7d8cca8e-bc35-4156-a965-0b32123691bc"
    }
})
