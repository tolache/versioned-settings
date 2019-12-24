package PizzaEvaluator.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object PizzaEvaluator_Build : BuildType({
    name = "Build"

    vcs {
        root(PizzaEvaluator.vcsRoots.PizzaEvaluator_HttpsTolacheDevAzureComTolachePizzaEvaluatorGitPizzaEvaluatorRefsHeadsMaster)
    }

    steps {
        visualStudio {
            path = "Pizza_Evaluator.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2019
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V16_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V16_0
        }
    }

    triggers {
        vcs {
        }
    }
})
