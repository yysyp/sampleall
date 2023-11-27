import groovy.json.JsonOutput
import org.devops.gitTools

def call(def baseFile = 'pipeline/base.yaml', def versionFile = 'pipeline/version.yaml') {

    withTools.colorTools("start initializeAppConfig", "blue");
    def baseData = readYaml(file: baseFile)

    println("the baseFile content is :" + baseData)

    checkInfo = gitTools.checkout("https://qrwrw/bb.git", "DEVOPS-GIT-CRED", "main", "https://nexusxa.x.com/q", "DEVOPS-NEXUS-CRED")
    initCommitDir = checkInfo["workDir"]
    repoName = checkInfo["repoName"]

    return baseData
}

private def init_info(id) {
    Map theInfo = [
            "aa": "aax",
            "bb": "xwer${id}"
    ]
    return theInfo
}
