package ProjectOnSvn

import ProjectOnSvn.buildTypes.*
import ProjectOnSvn.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("ProjectOnSvn")
    name = "Project on SVN"
    description = "SVN repository test"

    vcsRoot(ProjectOnSvn_SVNRepository)

    buildType(ProjectOnSvn_Bank)

    params {
        param("env.rev_number", "8.0")
    }
})
