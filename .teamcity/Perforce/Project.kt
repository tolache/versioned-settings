package Perforce

import Perforce.buildTypes.*
import Perforce.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Perforce")
    name = "Perforce"

    vcsRoot(Perforce_QAPerforce)

    buildType(Perforce_Build)
})
