def buildJar(){
    echo "building the jar image"
    sh "mvn package"
}

def buildImageAndPush(){
    echo "building the docker image"
    withCredentials([usernamePassword(credentialsId:'github',usernameVariable:'USER',passwordVariable:'PASS')]){
        sh "docker build sohail233/devops:jma-dockerHub-1.0"
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push sohail233/devops:jma-dockerHub-1.0"
    }
}

def deployApp(){
    echo "deploying application"
}

return this