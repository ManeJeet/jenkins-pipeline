pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building the code using Maven'
                echo 'maven build'
            }
        }
        stage('Unit and Integration Tests') {
            steps {
                echo 'Running unit tests with JUnit'
                echo 'Running integration tests with Selenium'
            }
            post {
                success{
                    emailext attachLog: true,
                    to: "jeetmane3801@gmail.com",
                    subject: "Test Stage Status",
                    body: "Dear Client,\n\nThe Test stage has completed with status.\nPlease find the test logs attached."
                }
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
            }
            post {
                success{
                    emailext attachLog: true,
                    to: "jeetmane3801@gmail.com",
                    subject: "Security Scan Status",
                    body: "Dear Client,\n\nThe Security Scan stage has completed with status.\nPlease find the security scan logs attached."
                    }
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
} 
