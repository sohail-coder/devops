def gv
pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage('init'){
            steps{
                script{
                    gv = load "script.groovy"
                }
            }
        }
        stage('build') {
            steps {
                script{
                    echo "Checking webhook 1"
                    echo 'build progress'
                    gv.buildJar()
                }
            }
        }
        stage('push') {
            steps {
                script{
                    echo 'testing progress'
                    gv.buildImageAndPush()
                }
            }
        }
        stage('deploy') {
            steps {
                script{
                    echo 'deploy progress'
                    gv.deployApp()
                }
            }
        }
    }
   
}
