package Perforce.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.PerforceVcsRoot

object Perforce_QAPerforce : PerforceVcsRoot({
    name = "QAPerforce"
    port = "172.31.128.103:1666"
    mode = stream {
        streamName = "//tola/files"
        enableFeatureBranches = true
        branchSpec = "+://tola/*"
    }
    userName = "jetbrains"
    password = "credentialsJSON:ee38efdf-b89a-45a0-9056-d736df00c0d1"
    workspaceOptions = """
        Options:        noallwrite clobber nocompress unlocked nomodtime rmdir
        Host:           %teamcity.agent.hostname%
        SubmitOptions:  revertunchanged
        LineEnd:        local
    """.trimIndent()
    runP4Clean = true
    param("client", "tola-ubuntu")
})
