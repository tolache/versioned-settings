package TestbedAntSample.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object TestbedAntSample_HttpsGithubComTolacheTestbedAntSampleRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/tolache/testbed-ant-sample#refs/heads/master"
    url = "https://github.com/tolache/testbed-ant-sample"
    authMethod = password {
        userName = "tolache"
        password = "credentialsJSON:7d8cca8e-bc35-4156-a965-0b32123691bc"
    }
})
