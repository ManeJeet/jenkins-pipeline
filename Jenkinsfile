pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building the code using Maven'
                echo 'mvn clean package'
                // Maven is used here as the build automation tool to compile and package the code
            }
        }
        
        stage('Unit and Integration Tests') {
            steps {
                echo 'Running unit tests with JUnit'
                echo 'mvn test'
                echo 'Running integration tests with Selenium'
                echo 'mvn integration-test'
                // JUnit is used for unit testing, Selenium for integration testing
            }
            post {
                success {
                    emailext (
                    subject: "Pipeline Successful",
                    body: "The pipeline has completed successfully. \n\nCheck the attached log for details.",
                    to: "jeetmane3801@gmail.com",
                    attachLog: true
                    )
                }
                failure {
                    emailext (
                    subject: "Pipeline Failed",
                    body: "The pipeline has failed. \n\nCheck the attached log for details.",
                    to: "jeetmane3801@gmail.com",
                    attachLog: true
                    )
                }
            }
        } 
        stage('Code Analysis') {
            steps {
                echo 'Analyzing code with SonarQube'
                echo 'mvn sonar:sonar'
                // SonarQube is used for static code analysis to ensure code quality
            }
        }
        
        stage('Security Scan') {
            steps {
                echo 'Performing security scan with OWASP Dependency-Check'
                echo 'mvn org.owasp:dependency-check-maven:check'
                // OWASP Dependency-Check is used to identify vulnerabilities in project dependencies
            }
            post {
        success {
            emailext (
                subject: "Pipeline Successful",
                body: "The pipeline has completed successfully. \n\nCheck the attached log for details.",
                to: "jeetmane3801@gmail.com",
                attachLog: true
            )
        }
        failure {
            emailext (
                subject: "Pipeline Failed",
                body: "The pipeline has failed. \n\nCheck the attached log for details.",
                to: "jeetmane3801@gmail.com",
                attachLog: true
            )
        }
        }
        }
        
        stage('Deploy to Staging') {
            steps {
                echo 'Deploying to AWS EC2 staging server'
                echo 'ansible-playbook -i inventory/staging deploy.yml'
                // Ansible is used here to deploy the application to a staging EC2 instance
            }
        }
        
        stage('Integration Tests on Staging') {
            steps {
                echo 'Running integration tests on staging environment with Postman/Newman'
                echo 'newman run StagingTests.json'
                // Postman/Newman is used for running API tests in the staging environment
            }
        }
        
        stage('Deploy to Production') {
            steps {
                echo 'Deploying to AWS EC2 production server'
                echo 'ansible-playbook -i inventory/production deploy.yml'
                // Ansible is used again to deploy the application to a production EC2 instance
            }
        }
    }
    
    
}
