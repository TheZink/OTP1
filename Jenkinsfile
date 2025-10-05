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

        stage('Test') {
                    steps {
                        script {
                            if (isUnix()) {
                                sh 'mvn -f demo/pom.xml test'
                            } else {
                                bat 'mvn -f demo/pom.xml test'
                            }
                        }
                    }
                }

        stage('Code Coverage') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -f demo/pom.xml jacoco:report'
                    } else {
                        bat 'mvn -f demo/pom.xml jacoco:report'
                    }
                }
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

        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -f demo/pom.xml clean package -DskipTests'
                    } else {
                        bat 'mvn -f demo/pom.xml clean package -DskipTests'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    if (isUnix()) {
                        sh "docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} ."
                    } else {
                        bat "docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} ."
                    }
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
                    steps {
                        script {
                            docker.withRegistry('https://index.docker.io/v1/', env.DOCKERHUB_CREDENTIALS_ID) {
                                docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                            }
                        }
                    }
                }
    }

    post {
            always {
                junit '**/target/surefire-reports/*.xml'
                jacoco execPattern: '**/target/jacoco.exec'
            }
        }
}