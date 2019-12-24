package HelloWorld.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object HelloWorld_HttpsGithubComTolachePowerShellTestRefsHeadsMaster : GitVcsRoot({
    name = "PowerShellTest"
    url = "https://github.com/tolache/PowerShellTest"
    authMethod = password {
        userName = "tolache"
        password = "credentialsJSON:7d8cca8e-bc35-4156-a965-0b32123691bc"
    }
})
