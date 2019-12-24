package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object MaximumNumberOfBuilds : Template({
    name = "maximumNumberOfBuilds"

    maxRunningBuilds = 10
})
