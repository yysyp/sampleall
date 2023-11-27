import groovy.json.JsonOutput


def call() {
    println 'This is cibuild leveraging docker image'
}

def mvnBuild() {
    try {
        def scriptPath = libraryResource "script/dockermvn.sh"
        def mvnSettings = "xxx.xml"
        sh "${scriptPath} mvn compile package -U -Dmaven.test.skip=true -Dsettings.security=${mvnSettings}"
    } catch(ex) {
        error "mvn compile error ${ex}"
    }
}

