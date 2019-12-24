package Idea

import Idea.buildTypes.*
import Idea.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Idea")
    name = "IDEA"

    vcsRoot(Idea_HttpsGithubComTolacheTestPrRefsHeadsMaster)
    vcsRoot(Idea_JavaSample)
    vcsRoot(Idea_HttpsGithubComTolacheTw62337refsHeadsMaster)

    buildType(Idea_Idea201913)
    buildType(Idea_Idea201923)
    buildType(Idea_TestBuildInspections)
})
