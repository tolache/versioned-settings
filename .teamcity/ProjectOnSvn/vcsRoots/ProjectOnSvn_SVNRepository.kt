package ProjectOnSvn.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.SvnVcsRoot

object ProjectOnSvn_SVNRepository : SvnVcsRoot({
    name = "BankSVN"
    url = "https://UNIT-905.Labs.IntelliJ.Net:8443/svn/BankSVN/"
    userName = "SVNuser"
    password = "credentialsJSON:ceeacea7-42f9-4fe1-9104-f51fd199a848"
    enableNonTrustedSSL = true
})
