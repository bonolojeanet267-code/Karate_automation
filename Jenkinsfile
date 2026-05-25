pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/bonolojeanet267-code/karate-automation.git', branch: 'master'
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
            publishHTML([
                reportDir: 'target/karate-reports',
                reportFiles: 'karate-summary.html',
                reportName: 'Karate Test Report'
            ])
            junit 'target/surefire-reports/*.xml'
        }
    }
}