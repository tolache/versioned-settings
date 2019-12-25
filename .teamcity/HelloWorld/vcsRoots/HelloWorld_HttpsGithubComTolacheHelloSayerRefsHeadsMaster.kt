package HelloWorld.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object HelloWorld_HttpsGithubComTolacheHelloSayerRefsHeadsMaster : GitVcsRoot({
    name = "HelloSayer"
    url = "https://github.com/tolache/HelloSayer"
    branchSpec = """
        +:refs/heads/develop
        +:refs/heads/master
    """.trimIndent()
    userNameStyle = GitVcsRoot.UserNameStyle.NAME
    authMethod = password {
        userName = "tolache"
        password = "credentialsJSON:7d8cca8e-bc35-4156-a965-0b32123691bc"
    }
})
