pipeline {
    agent any 
    
    environment {
        // CHANGE THIS to your actual Docker Hub username
        DOCKER_USER = 'bhavzzzzzz' 
        IMAGE_NAME = 'java-calculator'
        // This matches the ID you created in Step 1
        REGISTRY_CRED_ID = 'docker-hub-login' 
    }

    stages {
        stage('Build Docker Image') {
            steps {
                // We must include your username in the tag for Docker Hub to accept it
                sh "docker build -t ${DOCKER_USER}/${IMAGE_NAME}:latest ."
            }
        }
        
        stage('Run Tests') {
            steps {
                // Run the test on the local image
                sh "docker run --rm ${DOCKER_USER}/${IMAGE_NAME}:latest AppTest"
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    // This block securely retrieves username/password from Jenkins
                    withCredentials([usernamePassword(credentialsId: REGISTRY_CRED_ID, passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        // 1. Log in (using --password-stdin is safer than typing it out)
                        sh 'echo $PASS | docker login -u $USER --password-stdin'
                        
                        // 2. Push the image
                        sh "docker push ${DOCKER_USER}/${IMAGE_NAME}:latest"
                    }
                }
            }
        }
    }
    
    post {
        always {
            // Log out to be safe
            sh 'docker logout'
            // Clean up local image to save disk space
            sh "docker rmi ${DOCKER_USER}/${IMAGE_NAME}:latest || true"
        }
    }
}