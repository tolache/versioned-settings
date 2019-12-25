package BuildChain

import BuildChain.buildTypes.*
import BuildChain.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("BuildChain")
    name = "Build Chain"
    description = "Test for 2271902"

    vcsRoot(BuildChain_RepoB)
    vcsRoot(BuildChain_RepoC)
    vcsRoot(BuildChain_RepoA)

    buildType(BuildChain_BuildC)
    buildType(BuildChain_BuildB)
    buildType(BuildChain_BuildA)
    buildType(BuildChain_Composite)
})
