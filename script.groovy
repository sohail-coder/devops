def buildJar(){
    echo "building the jar image"
    sh "mvn package"
}

def buildImageAndPush(){
    echo "building the docker image"
    withCredentials([usernamePassword(credentialsId:'aws',usernameVariable:'USER',passwordVariable:'PASS')]){
        sh "docker build -t 381492132752.dkr.ecr.us-east-1.amazonaws.com/java-maven-app:latest ."
        sh "echo $PASS | docker login --username AWS --password-stdin 381492132752.dkr.ecr.us-east-1.amazonaws.com"
        sh "docker push 381492132752.dkr.ecr.us-east-1.amazonaws.com/java-maven-app:latest"
    }
}

def deployApp(){
    echo "deploying application"
}

return this
