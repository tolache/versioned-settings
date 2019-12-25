package TfvcTestProject.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.TfsVcsRoot

object TfvcTestProject_TfsHttpsDevAzureComTolacheTfvcTestProject : TfsVcsRoot({
    name = "tfs: https://dev.azure.com/tolache/ ${'$'}/TFVC_Test_Project"
    url = "https://dev.azure.com/tolache"
    root = "${'$'}/TFVC_Test_Project"
    userName = "admin"
    password = "credentialsJSON:deecfbf8-21c1-4c39-b285-9e99836b7f79"
})
