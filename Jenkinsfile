pipeline {
    agent any

    environment {
        AWS_REGION = 'us-east-2'
        ECR_REPO = 'public.ecr.aws/y8b4p2z0/artifacts'
        IMAGE_NAME = 'artifacts'
        IMAGE_TAG = 'latest'
    }

    tools {
        maven 'maven-3.9.6'
    }

    stages {
        
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        
        stage('Build Maven Project') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${IMAGE_NAME} .'
                sh 'docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${ECR_REPO}:${IMAGE_TAG}'
            }
        }

        stage('Login to ECR') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'aws-creds', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                    sh '''
                        aws configure set aws_access_key_id $AWS_ACCESS_KEY_ID
                        aws configure set aws_secret_access_key $AWS_SECRET_ACCESS_KEY
                        aws ecr-public get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin public.ecr.aws/y8b4p2z0
                    '''
                }
            }
        }

        stage('Push to ECR') {
            steps {
                sh 'docker push ${ECR_REPO}:${IMAGE_TAG}'
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'Pipeline succeeded.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
