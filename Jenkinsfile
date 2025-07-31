pipeline {
    agent any

    environment {
        BRANCH = "feature"
    }

    parameters {
        booleanParam(name: "is_feature", defaultValue: false, description: "Is this a feature branch")
    }

    tools {
        maven 'maven'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    if (params.is_feature) {
                        echo "Pushing to feature branch"
                    } else {
                        echo "Pushing to main branch"
                    }
                }
                checkout scm
            }
        }

        stage('Build and Test') {
            parallel {
                stage("Build") {
                    steps {
                        dir('old_pipeline'){
                            sh 'mvn clean package'
                        }
                        
                    }
                }
                stage("Test") {
                    when {
                        expression {
                            return params.is_feature
                        }
                    }
                    steps {
                        sh 'mvn test'
                    }
                }
            }
        }

        stage("Deploy") {
            steps {
                echo "Deploying application..."
                    
                dir('old_pipeline') {
                        sh 'mvn deploy'
                
                }
            }
        }
    }

    post {
        success {
            mail to: "eziowilliams15@gmail.com",
                 subject: "Build successful on ${env.BRANCH_NAME}",
                 body: "The build for branch ${env.BRANCH_NAME} was successful."
        }
        failure {
            mail to: "eziowilliams15@gmail.com",
                 subject: "Build failed on ${env.BRANCH_NAME}",
                 body: "The build for branch ${env.BRANCH_NAME} has failed."
        }
        always {
            echo "Your Jenkins pipeline executed"
        }
    }
}