package SlnPlacementTest.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object SlnPlacementTest_HttpsGithubComTolacheSlnPlacementTestRefsHeadsMaster : GitVcsRoot({
    name = "SLN-Placement-Test"
    url = "https://github.com/tolache/SLN-Placement-Test"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "tolache"
        password = "credentialsJSON:7d8cca8e-bc35-4156-a965-0b32123691bc"
    }
})
