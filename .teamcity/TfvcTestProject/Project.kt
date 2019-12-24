package TfvcTestProject

import TfvcTestProject.buildTypes.*
import TfvcTestProject.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("TfvcTestProject")
    name = "TFVC Test Project"

    vcsRoot(TfvcTestProject_TcqaTfs2018)
    vcsRoot(TfvcTestProject_TfsHttpsDevAzureComTolacheTfvcTestProject)

    buildType(TfvcTestProject_Build)
    buildType(TfvcTestProject_BuildTfs)
})
