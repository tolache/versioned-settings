package MyMavenProject

import MyMavenProject.buildTypes.*
import MyMavenProject.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("MyMavenProject")
    name = "MyMavenProject"

    vcsRoot(MyMavenProject_JacocoMavenUnittestv2)

    buildType(MyMavenProject_Build)
})
