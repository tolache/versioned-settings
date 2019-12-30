package Idea.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object Idea_JavaSample : GitVcsRoot({
    name = "JavaSample"
    url = "https://github.com/tolache/JavaSample"
    authMethod = password {
        userName = "tolache"
        password = ""
    }
})
