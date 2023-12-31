### Jenkins system -> configuration -> Global Pipeline Libraries -> 
Source Code Management: Git
Project Repository: https://gitee.com/yysyp/my-sharedpipeline-lib.git
Credentials: xxx
Library Path (optional): 


### In Jenkinsfile use the @Library to use the configured shared lib:
@Library('pipeline-shared-lib1@master') _

pipeline {
    agent none
    ...
}

### Or directly use below code without config in the system config?
library identifier: 'pipeline-shared-lib1@master',
    retriever: modernSCM([$class: 'GitSCMSource',
                          remote: 'https://gitee.com/yysyp/my-sharedpipeline-lib.git',
                          credentialsId: 'xxx'
])

def config

pipeline {
    agent none
    options {
        buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '14', numToKeepStr: '')
        disableConcurrentBuilds()
        timestamps()
        ansiColor('xterm')
    }
    triggers {
        cron(env.BRANCH_NAME == 'master'? '''TZ=Asia/Hong_Kong 0 23 * * *''': '')
    }

    stages {
        stage('CI build') {
            agent { label 'linux' }
            stages {
                stage('Init Config') {
                    steps {
                        script {
                            config = initializeAppConfig('pipeline/base.yaml')
                            config.deployEnv = config.deploy.env.dev.id
                        }
                    }
                }
                stage('Code Build') {
                    when {
                        expression {return config.build.package.enable == true}
                    }
                    steps {
                        script {
                            config = codeBuild(config)
                        }
                    }
                }

            }
            post {
                always {
                    script {
                        cleanWorkspace()
                    }
                }
            }
        }
        stage('CD deploy env1') {
            when {
                expression {return config.deploy.enable == true}
                expression {return config.deploy.env1.enable == true}
            }
            agent { label config.deployAgentLabel.dev.env1 }
            steps {
                script {
                    replaceCredFromJenkins(PlaceHolder: 'databaseName, databasePass')
                    config = kustomizeDeploy(config)
                }
            }
        }

    }
    post {
        always {
            script {
                node('linux') {
                    postPipeline(config)
                }
            }
        }
    }
}

