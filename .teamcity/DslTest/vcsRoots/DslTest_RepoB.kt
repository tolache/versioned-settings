package DslTest.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object DslTest_RepoB : GitVcsRoot({
    uuid = "7aa20e42-a39a-4eae-9f36-22cf4e1f01ed"
    name = "repoB"
    url = "https://github.com/tolache/repoB"
    authMethod = password {
        userName = "tolache"
        password = "credentialsJSON:7d8cca8e-bc35-4156-a965-0b32123691bc"
    }
})
