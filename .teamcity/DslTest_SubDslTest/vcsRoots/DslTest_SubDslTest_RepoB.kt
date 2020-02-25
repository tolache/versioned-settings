package DslTest_SubDslTest.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object DslTest_SubDslTest_RepoB : GitVcsRoot({
    uuid = "4fb3c5d4-6a04-4c5a-bfb8-bab4eada7164"
    name = "repoB"
    url = "https://github.com/tolache/repoB"
    authMethod = password {
        userName = "tolache"
        password = "credentialsJSON:7d8cca8e-bc35-4156-a965-0b32123691bc"
    }
})
