pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building the code using Maven'
                echo 'mvn clean package'
            }
        }
        
        stage('Unit and Integration Tests') {
            steps {
                echo 'Running unit tests with JUnit'
                echo 'mvn test'
                echo 'Running integration tests with Selenium'
                echo 'mvn integration-test'
            }
        } 
        stage('Code Analysis') {
            steps {
                echo 'Analyzing code with SonarQube'
                echo 'mvn sonar:sonar'
            }
        }
        
        stage('Security Scan') {
            steps {
                echo 'Performing security scan with OWASP Dependency-Check'
                echo 'mvn org.owasp:dependency-check-maven:check'
            }
        }    
        
        stage('Deploy to Staging') {
            steps {
                echo 'Deploying to AWS EC2 staging server'
                echo 'ansible-playbook -i inventory/staging deploy.yml'
            }
        }
        
        stage('Integration Tests on Staging') {
            steps {
                echo 'Running integration tests on staging environment with Postman/Newman'
                echo 'newman run StagingTests.json'
            }
        }
        
        stage('Deploy to Production') {
            steps {
                echo 'Deploying to AWS EC2 production server'
                echo 'ansible-playbook -i inventory/production deploy.yml'
            }
        }
    }
    post {
        success {
            emailext (
                subject: "Pipeline Successful",
                body: "The pipeline has completed successfully. \n\nCheck the attached log for details.",
                to: "your-email@example.com",
                attachLog: true
            )
        }
        failure {
            emailext (
                subject: "Pipeline Failed",
                body: "The pipeline has failed. \n\nCheck the attached log for details.",
                to: "your-email@example.com",
                attachLog: true
            )
        }
    }
} 

