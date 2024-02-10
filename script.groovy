def buildJar(){
    echo "building the jar image"
    sh 'mvn clean build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
    sh "mvn clean package"

}

def buildImageAndPush(){
    echo "building the docker image"
    withCredentials([usernamePassword(credentialsId:'pat',usernameVariable:'USER',passwordVariable:'PASS')]){
        sh "docker build -t sohail233/devops:latest ."
        sh "echo $PASS | docker login --username $USER --password-stdin"
        sh "docker push sohail233/devops:latest"
    }
}

def deployApp(){
    echo "deploying application"
}
def commitToGit(){
    withCredentials([usernamePassword(credentialsId:'git',usernameVariable:'USER',passwordVariable:'PASS')]){
        sh 'git config --global user.email "sohail@gmail.com"'
        sh 'git config --global user.name "sohail"'
        sh 'ssh-keyscan -t ed25519 github.com'
        sh "git remote set-url origin https://$USER:$PASS@github.com/sohail-coder/devops.git"
        sh 'git add .'
        sh 'git commit -m "testing commit from jenkins"'
        sh 'git push origin HEAD:devOps' 
    }
}
// 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
return this
