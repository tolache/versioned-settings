package PizzaEvaluator

import PizzaEvaluator.buildTypes.*
import PizzaEvaluator.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.tfsIssueTracker

object Project : Project({
    id("PizzaEvaluator")
    name = "Pizza Evaluator"

    vcsRoot(PizzaEvaluator_HttpsTolacheDevAzureComTolachePizzaEvaluatorGitPizzaEvaluatorRefsHeadsMaster)

    buildType(PizzaEvaluator_Build)

    features {
        tfsIssueTracker {
            id = "PROJECT_EXT_15"
            displayName = "tolache Azure DevOps"
            host = "https://dev.azure.com/tolache/Pizza_Evaluator/"
            userName = ""
            password = "credentialsJSON:ec7e5900-df80-49a7-b537-58b1e6126ff3"
            pattern = """#(\d+)"""
        }
    }
})
