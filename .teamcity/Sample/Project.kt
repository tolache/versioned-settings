package Sample

import Sample.buildTypes.*
import Sample.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Sample")
    name = "Sample"

    vcsRoot(Sample_HttpsGithubComTolacheSampleNUnitTestProjectRefsHeadsMaster)

    buildType(Sample_Build)
})
