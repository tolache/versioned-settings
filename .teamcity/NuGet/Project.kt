package NuGet

import NuGet.buildTypes.*
import NuGet.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("NuGet")
    name = "NuGet"

    vcsRoot(NuGet_HttpsGithubComTolacheHelloWorldRefsHeadsMaster)
    vcsRoot(NuGet_HttpsGithubComTolacheHelloSayerRefsHeadsMaster)

    buildType(NuGet_HelloWorld)
    buildType(NuGet_HelloSayer)
})
