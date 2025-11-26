pipeline {
    agent {
        docker { image 'openjdk:21-slim' }
    }
    environment {
        // Create this credential in Jenkins (Username/password Credentials)
        DOCKER_CREDENTIALS_ID = 'dockerhub_creds'
        // Replace this value with your Docker Hub repo (e.g., 'youruser/your-repo')
        DOCKER_IMAGE = 'bhavzzzzzz/imt2023050'
        // Tag to use for the image; defaults to build number, or override as needed
        DOCKER_TAG = "${env.BUILD_NUMBER}"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mkdir -p out'
                sh 'javac -d out *.java'
            }
        }
        stage('Test') {
            steps {
                script {
                    def status = sh(script: 'java -cp out AppTest', returnStatus: true)
                    if (status != 0) {
                        error("Tests failed (exit code ${status})")
                    }
                }
            }
        }
        stage('Docker Build & Push') {
            agent any
            steps {
                script {
                    if (!fileExists('Dockerfile')) {
                        echo 'No Dockerfile found; skipping Docker build and push.'
                    } else {
                        withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                            sh "docker --version || true"
                            sh 'docker login -u "$DOCKER_USER" -p "$DOCKER_PASS"'
                            sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                            sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                            // Optional: Push the 'latest' tag
                            sh "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest || true"
                            sh "docker push ${DOCKER_IMAGE}:latest || true"
                            sh 'docker logout || true'
                        }
                    }
                }
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'out/**', fingerprint: true
            }
        }
    }
    post {
        success {
            echo "Build and tests passed"
        }
        failure {
            echo "Build or tests failed"
        }
    }
}
