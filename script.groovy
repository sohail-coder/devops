def buildJar(){
    echo "building the jar image"
    sh "mvn package"
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

return this
