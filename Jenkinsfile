pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Run Karate API Tests') {
            steps {
                bat 'mvn clean test -Dtest=TestRunner'
            }
            post {
                always {
                    // Publish Karate HTML report
                    publishHTML(target: [
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/karate-reports',
                        reportFiles: 'karate-summary.html',
                        reportName: 'Karate Test Report'
                    ])
                    
                    // Publish JUnit results
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Run Playwright UI Tests') {
            steps {
                // Install Node.js dependencies
                bat 'npm ci'
                
                // Install Playwright browsers
                bat 'npx playwright install --with-deps'
                
                // Run Playwright tests with JSON reporter
                bat 'npx playwright test --reporter=json,junit,html'
            }
            post {
                always {
                    // Publish Playwright HTML report
                    publishHTML(target: [
                        allowMissing: true,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'playwright-report',
                        reportFiles: 'index.html',
                        reportName: 'Playwright Test Report'
                    ])
                    
                    // Publish JUnit results (if you have them)
                    junit 'junit.xml'
                    
                    // Archive test results
                    archiveArtifacts artifacts: 'test-results.json, junit.xml, playwright-report/**', allowEmptyArchive: true
                }
            }
        }
    }
    
    post {
        always {
            // Clean up
            cleanWs()
        }
    }
}