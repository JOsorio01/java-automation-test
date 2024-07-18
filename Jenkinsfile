pipeline {

    agent any

    stages {
        stage('Build Jar') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build Image') {
            steps {
                sh "docker build -t josorio001/selenium-docker:latest ."
            }
        }

        stage('Pushing Image ') {
            environment {
                DOCKER_HUB = credentials("dockerhub-credentials")
            }
            steps {
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh "docker push josorio001/selenium-docker:latest"
                sh "docker tag josorio001/selenium-docker josorio001/selenium-docker:${env.BUILD_NUMBER}"
                sh "docker push josorio001/selenium-docker:${env.BUILD_NUMBER}"
            }
        }
    }

    post {
        always {
            sh "docker system prune -f"
            sh "docker logout"
        }
    }
}
