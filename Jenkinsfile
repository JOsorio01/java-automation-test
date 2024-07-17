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
                sh "docker build -t josorio001/selenium-docker ."
            }
        }

        stage('Pushing Image ') {
            environment {
                DOCKER_HUB = environment("dockerhub-credentials")
            }
            steps {
                sh 'docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}'
                sh "docker push josorio001/selenium-docker"
            }
        }
    }

    post {
        always {
            sh "docker logout"
        }
    }
}
