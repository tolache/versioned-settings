package MyMavenProject.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object MyMavenProject_JacocoMavenUnittestv2 : GitVcsRoot({
    name = "jacoco-maven-unittestv2"
    url = "https://github.com/tolache/jacoco-maven-unittestv2.git"
    authMethod = password {
        userName = "tolache"
        password = "credentialsJSON:7d8cca8e-bc35-4156-a965-0b32123691bc"
    }
})
