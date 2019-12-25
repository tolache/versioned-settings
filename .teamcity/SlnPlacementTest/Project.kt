package SlnPlacementTest

import SlnPlacementTest.buildTypes.*
import SlnPlacementTest.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("SlnPlacementTest")
    name = "SLN Placement Test"

    vcsRoot(SlnPlacementTest_HttpsGithubComTolacheSlnPlacementTestRefsHeadsMaster)

    buildType(SlnPlacementTest_Build)
})
