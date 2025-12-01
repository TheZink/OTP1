pipeline {
    agent any

    environment {
        //PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
        PATH = "${JAVA_HOME}\\bin;${JMETER_HOME}\\bin;${env.PATH}"
        JMETER_HOME = 'C:\\Tools\\apache-jmeter-5.6.3'

        SONARQUBE_SERVER = 'SonarQubeServer' // The name of the SonarQube server configured in Jenkins
        SONAR_TOKEN = 'sqa_3b2b639e2c536a7095d2bd438b6e0a0a4bbafc07' // Store the token securely

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


        //stage('Publish Test Results') {
            //steps {
                //junit '**/target/surefire-reports/*.xml'
            //}
        //}


        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv("${env.SONARQUBE_SERVER}") {
                        if (isUnix()) {
                            sh """
                                mvn -f demo/pom.xml sonar:sonar \
                                  -Dsonar.projectKey=SonarOTP2 \
                                  -Dsonar.projectName=OTP2 \
                                  -Dsonar.projectVersion=1.0 \
                                  -Dsonar.sources=src \
                                  -Dsonar.java.binaries=target/classes \
                                  -Dsonar.java.libraries=target/classes \
                                  -Dsonar.issue.exclusions=src/test/**/* \
                                  -Dsonar.tests=src/test \
                                  -Dsonar.test.inclusions=src/test/**/*.java \
                                  -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml \
                                  -Dsonar.login=${env.SONAR_TOKEN}
                            """
                        } else {
                            bat """
                                mvn -f demo/pom.xml sonar:sonar ^
                                  -Dsonar.projectKey=SonarOTP2 ^
                                  -Dsonar.projectName=OTP2 ^
                                  -Dsonar.projectVersion=1.0 ^
                                  -Dsonar.sources=src ^
                                  -Dsonar.java.binaries=target/classes ^
                                  -Dsonar.java.libraries=target/classes ^
                                  -Dsonar.issue.exclusions=src/test/**/* ^
                                  -Dsonar.tests=src/test ^
                                  -Dsonar.test.inclusions=src/test/**/*.java ^
                                  -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml ^
                                  -Dsonar.login=${env.SONAR_TOKEN}
                            """
                        }
                    }
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -f demo/pom.xml clean package' //-DskipTests
                    } else {
                        bat 'mvn -f demo/pom.xml clean package' //-DskipTests
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

        stage('Non-Functional Test') {
            steps {
                bat 'jmeter -n -t tests/performance/demo.jmx -l result.jtl'
            }
        }

        stage('Push Docker Image to Docker Hub') {
                    steps {
                        script {
                            withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                                docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                            }
                        }
                    }
                }
    }
}