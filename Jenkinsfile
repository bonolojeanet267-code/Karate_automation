pipeline {
    agent any

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
                bat 'mvn test -Dtest=uitests.SauceDemoCheckoutTest'
            }
        }
    }

    post {
        always {

            // Requires HTML Publisher plugin
            publishHTML([
                reportDir: 'target/karate-reports',
                reportFiles: 'karate-summary.html',
                reportName: 'Karate Test Report'
            ])

            junit 'target/surefire-reports/*.xml'
        }
    }
}