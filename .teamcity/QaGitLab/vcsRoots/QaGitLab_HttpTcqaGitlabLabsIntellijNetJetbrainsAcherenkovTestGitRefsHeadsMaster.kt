package QaGitLab.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object QaGitLab_HttpTcqaGitlabLabsIntellijNetJetbrainsAcherenkovTestGitRefsHeadsMaster : GitVcsRoot({
    name = "http://tcqa-gitlab.labs.intellij.net/jetbrains/acherenkov-test.git#refs/heads/master"
    url = "http://tcqa-gitlab.labs.intellij.net/jetbrains/acherenkov-test.git"
    branchSpec = """
        +:refs/heads/*
        -:refs/heads/develop
    """.trimIndent()
    authMethod = password {
        userName = "jetbrains"
        password = "credentialsJSON:ee38efdf-b89a-45a0-9056-d736df00c0d1"
    }
})
