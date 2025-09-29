pipeline {
    agent any

    environment {
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"

        // Define Docker Hub credentials ID
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        // Define Docker Hub repository name
        DOCKERHUB_REPO = 'viridescentful/otp1project'
        // Define Docker image tag
        DOCKER_IMAGE_TAG = 'latest'
    }

    tools {
        maven 'Default'
    }

    stages {
        stage('checking') {
            steps {
                echo 'Building...'
                git branch:'main', url:'https://github.com/TheZink/OTP1.git'
            }
        }

        stage('build') {
            steps {
                bat 'mvn -f demo/pom.xml clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn -f demo/pom.xml test'
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn -f demo/pom.xml jacoco:report'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }

        stage('Build Docker Image') {
            steps {
                 bat 'docker build -t %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG% .'
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                 withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                      bat '''
                           docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                           docker push %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG%
                      '''
                 }
            }
        }
    }
}