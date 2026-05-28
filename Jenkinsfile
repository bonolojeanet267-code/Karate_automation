pipeline {
agent any

```
tools {
    maven 'Maven-3.9.14'
    jdk 'JDK17'
}

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
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/karate-reports',
                    reportFiles: 'karate-summary.html',
                    reportName: 'Karate Test Report'
                ])

                // Publish JUnit results
                junit allowEmptyResults: true,
                       testResults: 'target/surefire-reports/*.xml'
            }
        }
    }

    stage('Run Playwright UI Tests') {
        steps {

            // Install dependencies
            bat 'npm ci'

            // Install Playwright browsers
            bat 'npx playwright install'

            // Run Playwright tests
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

                // Publish JUnit results
                junit allowEmptyResults: true,
                       testResults: 'junit.xml'

                // Archive reports
                archiveArtifacts artifacts: 'playwright-report/**, junit.xml',
                                 allowEmptyArchive: true
            }
        }
    }
}

post {
    always {
        cleanWs()
    }
}
```

}
