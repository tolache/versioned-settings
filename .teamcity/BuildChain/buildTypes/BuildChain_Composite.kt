package BuildChain.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object BuildChain_Composite : BuildType({
    name = "Composite"

    type = BuildTypeSettings.Type.COMPOSITE

    params {
        param("default.branch.name", "${BuildChain_BuildA.reverseDepParamRefs["default.branch.name"]}")
        text("reverse.dep.BuildChain_BuildA.default.branch.name", "master", display = ParameterDisplay.PROMPT, allowEmpty = false)
    }

    vcs {
        root(BuildChain.vcsRoots.BuildChain_RepoA, "+:. => A")
        root(BuildChain.vcsRoots.BuildChain_RepoB, "+:. => B")
        root(BuildChain.vcsRoots.BuildChain_RepoC, "+:. => C")

        showDependenciesChanges = true
    }

    dependencies {
        snapshot(BuildChain_BuildB) {
        }
        snapshot(BuildChain_BuildC) {
        }
    }
})
