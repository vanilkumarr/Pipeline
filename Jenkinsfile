pipeline{
    agent any

    tools {
        maven 'M3'
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