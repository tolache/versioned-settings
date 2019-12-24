package QaGitLab

import QaGitLab.buildTypes.*
import QaGitLab.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("QaGitLab")
    name = "QA GitLab"

    vcsRoot(QaGitLab_HttpTcqaGitlabLabsIntellijNetJetbrainsAcherenkovTestGitRefsHeadsMaster)

    buildType(QaGitLab_PowerShell)
    buildType(QaGitLab_Bank)
})
