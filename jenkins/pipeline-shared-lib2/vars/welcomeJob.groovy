def call(String name = 'User') {
def script = this
    node {
        echo "Welcome, ${name}. this="+this
        //script.logger.debug ("hihi this is from script...")
        def cmd = 'echo "hahahah this is from bat echo."'
        //script.bat(script: cmd)
        echo "before running bat..."
        println 'test1'
        script.println 'test2'
        script.stage('hahaha') {
            println 'test3'
            if (name != null && name.equals("cc")) {
                def scm = [$class: 'GitSCM', 
                                branches: [[name: "*/${name}"]], 
                                doGenerateSubmoduleConfigurations: false, 
                                extensions: [], 
                                submoduleCfg: [], 
                                userRemoteConfigs: script.scm.userRemoteConfigs
                        ];
                println 'test4'
                def checkout = script.checkout(scm)
                println 'test5'
                print "checkout = "+checkout
            }
            println 'test6'
            exitCode = script.bat(returnStatus: true, script: '''
                    echo "ni hao"
                    ''')
            println exitCode
        }
    }


}