pipeline {

    agent any

    stages {
        stage('Build Jar') {
            steps {
                sh "mvn clean package -DskipTest"
            }
        }

        stage('Build Image') {
            steps {
                sh "docker build -t josorio001/selenium-docker ."
            }
        }

        stage('Pushing Image ') {
            steps {
                sh "docker push josorio001/selenium-docker"
            }
        }
    }
}
