package org.devops
import groovy.json.JsonOutput

def checkout(gitUrl, gitCredentialId, branchName, nexusUrl, nexusCredentialId) {
    println 'checkout...'
    def gitUrlName = gitUrl.split("https://")[1]
    def repoName = gitUrl.split("/")[-1].split("\\.")[0]
    def workDir = "${env.WORKSPACE}/${repoName}"
    sh """
        if [[ -d ${workDir} ]]; then
            rm -rf ${workDir}
        fi
        mkdir ${workDir}
    """

    //withCredentials()


    def gitInfo = [:]
    gitInfo.put("workDir", workDir)
    gitInfo.put("repoName", repoName)
    return gitInfo

}