package teamcity_nunit_samples.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object teamcity_nunit_samples_1 : GitVcsRoot({
    id("teamcity_nunit_samples")
    name = "root"
    url = "https://github.com/JetBrains/teamcity-nunit-samples.git"
})
