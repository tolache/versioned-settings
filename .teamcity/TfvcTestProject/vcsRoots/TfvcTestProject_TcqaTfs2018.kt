package TfvcTestProject.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.TfsVcsRoot

object TfvcTestProject_TcqaTfs2018 : TfsVcsRoot({
    name = "tcqa-tfs2018"
    url = "http://tcqa-tfs2018:81/InitialCollection/"
    root = "${'$'}/Bank"
    userName = "jetbrains"
    password = "credentialsJSON:ee38efdf-b89a-45a0-9056-d736df00c0d1"
})
