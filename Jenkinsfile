pipeline{
    agent any

    tools {
        maven 'maven:3.9.6-eclipse-temurin-17'
    }

    stages {
        stage('Build'){
            steps{
                sh 'mvn clean install'
            }
        }

        stage('Archive'){
            steps{
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
}