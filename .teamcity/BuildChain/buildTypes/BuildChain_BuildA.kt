package BuildChain.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object BuildChain_BuildA : BuildType({
    name = "Build A"

    vcs {
        root(BuildChain.vcsRoots.BuildChain_RepoA)
    }

    steps {
        script {
            scriptContent = """
                echo "file.txt content:"
                cat file.txt
                echo "##teamcity[testStarted   flowId='0' name='test0']"
                echo "##teamcity[testStarted   flowId='1' name='test1']"
                echo "##teamcity[testFailed    flowId='0' name='test0']"
                echo "##teamcity[message     flowId='0' text='about test0']"
                echo "##teamcity[message     flowId='1' text='about test1']"
                echo "##teamcity[testFinished flowId='0' name='test0']"
                echo "##teamcity[testFinished flowId='1' name='test1']"
                echo "##teamcity[testMetadata testName='test0' name='a url' type='link' value='https://apple.com']"
            """.trimIndent()
        }
    }
})
