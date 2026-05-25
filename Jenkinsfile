pipeline {
    agent any

     environment {
        // LambdaTest credentials from Jenkins secrets
        LT_USERNAME = credentials('lambdatest-username')
        LT_ACCESS_KEY = credentials('lambdatest-access-key')
    }

    tools {
        maven 'Maven 3.9'
        // nodejs 'Node 20'
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
                        bat '''
                        echo "Running Playwright UI Tests with LambdaTest"
                        set LT_USERNAME=%LT_USERNAME%
                        set LT_ACCESS_KEY=%LT_ACCESS_KEY%
                        mvn test -Dtest=SauceDemoCheckoutTest -pl . --no-transfer-progress
                        '''

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