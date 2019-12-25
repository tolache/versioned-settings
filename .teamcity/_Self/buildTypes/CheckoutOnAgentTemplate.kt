package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CheckoutOnAgentTemplate : Template({
    name = "Checkout_On_Agent_Template"
    description = "This is a template that sets checkout to agent-side"

    vcs {
        checkoutMode = CheckoutMode.ON_AGENT
    }
})
