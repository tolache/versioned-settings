package teamcity_nunit_samples

import teamcity_nunit_samples.buildTypes.*
import teamcity_nunit_samples.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("teamcity_nunit_samples")
    name = "teamcity_nunit_samples"

    vcsRoot(teamcity_nunit_samples_1)

    buildType(HpsCsharpNunit_Build)
})
