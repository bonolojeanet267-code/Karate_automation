pipeline {
    agent any

    tools {
        maven 'Maven 3.9'
        nodejs 'Node 20'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master',
                    credentialsId: 'github-token',
                    url: 'https://github.com/bonolojeanet267-code/Karate_automation.git'
            }
        }

        stage('Run Karate API Tests') {
            steps {
                bat 'mvn clean test -Dtest=TestRunner'
            }
        }

        stage('Run Playwright UI Tests') {
            steps {
                bat 'npm install'
                bat 'npx playwright install --with-deps'
                bat 'npx playwright test'
            }
        }
    }

    post {
        always {
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/karate-reports',
                reportFiles: 'karate-summary.html',
                reportName: 'Karate Test Report'
            ])

            junit allowEmptyResults: true,
                  testResults: '**/surefire-reports/*.xml'
        }
    }
}