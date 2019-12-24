package TestbedAntSample

import TestbedAntSample.buildTypes.*
import TestbedAntSample.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("TestbedAntSample")
    name = "Testbed Ant Sample"

    vcsRoot(TestbedAntSample_HttpsGithubComTolacheTestbedAntSampleRefsHeadsMaster)

    buildType(TestbedAntSample_Build)
})
