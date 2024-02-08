def buildJar(){
    echo "building the jar image"
    sh "mvn package"
}

def buildImageAndPush(){
    echo "building the docker image"
    withCredentials([usernamePassword(credentialsId:'aws',usernameVariable:'USER',passwordVariable:'PASS')]){
        sh "docker build -t sohail233/devops:jma-aws-ecr-1.0 ."
        sh "echo $PASS | docker login --username AWS --password-stdin 381492132752.dkr.ecr.us-east-1.amazonaws.com"
        sh "docker push sohail233/devops:jma-aws-ecr-1.0 ."
    }
}

def deployApp(){
    echo "deploying application"
}

return this
