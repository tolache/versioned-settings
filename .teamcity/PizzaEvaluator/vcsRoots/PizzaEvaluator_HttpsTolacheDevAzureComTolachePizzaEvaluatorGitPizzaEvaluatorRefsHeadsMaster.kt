package PizzaEvaluator.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object PizzaEvaluator_HttpsTolacheDevAzureComTolachePizzaEvaluatorGitPizzaEvaluatorRefsHeadsMaster : GitVcsRoot({
    name = "https://tolache@dev.azure.com/tolache/Pizza_Evaluator/_git/Pizza_Evaluator#refs/heads/master"
    url = "https://tolache@dev.azure.com/tolache/Pizza_Evaluator/_git/Pizza_Evaluator"
    authMethod = password {
        password = "credentialsJSON:ec7e5900-df80-49a7-b537-58b1e6126ff3"
    }
})
