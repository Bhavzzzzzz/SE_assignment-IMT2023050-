pipeline {
    agent any 
    
    environment {
        // Your Docker Hub credentials
        DOCKER_USER = 'bhavzzzzzz' 
        IMAGE_NAME = 'imt2023050'
        REGISTRY_CRED_ID = 'docker-hub-login' 
    }

    stages {
        stage('Build Docker Image') {
            steps {
                // CHANGED: 'sh' -> 'bat'
                bat "docker build -t ${DOCKER_USER}/${IMAGE_NAME}:latest ."
            }
        }
        
        stage('Run Tests') {
            steps {
                // CHANGED: 'sh' -> 'bat'
                bat "docker run --rm ${DOCKER_USER}/${IMAGE_NAME}:latest AppTest"
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: REGISTRY_CRED_ID, passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        // WINDOWS SPECIFIC LOGIN:
                        // 1. We use 'bat' instead of 'sh'
                        // 2. We use %VAR% syntax for Windows variables
                        bat 'echo %PASS% | docker login -u %USER% --password-stdin'
                        
                        bat "docker push ${DOCKER_USER}/${IMAGE_NAME}:latest"
                    }
                }
            }
        }
    }
    
    post {
        always {
            // CHANGED: 'sh' -> 'bat'
            bat 'docker logout'
            // In Windows, '|| true' doesn't work the same. 
            // We usually just let it run. If it fails, it fails (it's just cleanup).
            bat "docker rmi ${DOCKER_USER}/${IMAGE_NAME}:latest" 
        }
    }
}