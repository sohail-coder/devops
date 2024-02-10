def buildJar(){
    echo "building the jar image"
    sh 'mvn clean build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
    sh "mvn clean package"

}

def buildImageAndPush(){
    echo "building the docker image"
    withCredentials([usernamePassword(credentialsId:'dockerhub',usernameVariable:'USER',passwordVariable:'PASS')]){
        sh "docker build -t sohail233/devops:latest ."
        sh "echo $PASS | docker login --username $USER --password-stdin"
        sh "docker push sohail233/devops:latest"
    }
}

def deployApp(){
    echo "deploying application"
}
def commitToGit(){
    withCredentials([usernamePassword(credentialsId:'NewPat',usernameVariable:'USER',passwordVariable:'PASS')]){
        sh 'git config --global user.name "sohail-jenkins"'
        sh 'git config --global user.email "sohail-jenkins@gmail.com"'
        sh 'echo "pushing........"'
        sh "git remote set-url origin https://$USER:$PASS@github.com/sohail-coder/devops.git"
        sh 'git add .'
        sh 'git commit -m "testing commit from jenkins"'
        sh 'git push origin HEAD:devOps' 
    }
}
// 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
// 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
return this
