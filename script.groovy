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
        sh 'echo "pushing........"'
        sh "git remote set-url origin https://$USER:$PASS@github.com/sohail-coder/devops.git"
        sh 'git add .'
        sh 'git commit -m "ci-skip"'
        sh 'git push HEAD:devOps' 
    }
}
return this
