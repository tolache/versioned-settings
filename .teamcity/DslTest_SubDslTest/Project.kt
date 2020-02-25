package DslTest_SubDslTest

import DslTest_SubDslTest.buildTypes.*
import DslTest_SubDslTest.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    uuid = "4a1724fe-90b6-40ae-ab54-f480c5777602"
    id("DslTest_SubDslTest")
    parentId("DslTest")
    name = "Sub-DSL-Test"

    vcsRoot(DslTest_SubDslTest_RepoB)

    buildType(DslTest_SubDslTest_BuildB)
})
