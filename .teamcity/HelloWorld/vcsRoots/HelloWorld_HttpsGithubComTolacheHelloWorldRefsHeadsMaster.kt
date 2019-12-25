package HelloWorld.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object HelloWorld_HttpsGithubComTolacheHelloWorldRefsHeadsMaster : GitVcsRoot({
    name = "hello-world"
    url = "https://github.com/tolache/hello-world"
    branchSpec = "(refs/heads/*)"
    authMethod = password {
        userName = "tolache"
        password = "credentialsJSON:40bfcc6d-c307-478b-8d4e-a3f548dcf9f7"
    }
})
