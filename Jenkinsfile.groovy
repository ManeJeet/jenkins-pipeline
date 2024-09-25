pipeline {
   agent any
​
    ​environment {
        ​​DIRECTORY_PATH = "/"
        ​​TESTING_ENVIRONMENT = "test"
​       ​PRODUCTION_ENVIRONMENT = "Jeet Manoj Mane"
​   ​}
 
   stages {
       stage('Build') {
           steps {
                echo 'Building the code using Maven'
                ​​​​echo 'mvn clean package'
           }
       }
       stage('Unit and Integration Tests') {
           steps {
               echo 'Running unit tests with JUnit'
                echo 'mvn test'
                echo 'Running integration tests with Selenium'
           }
           post {
       success {
         
               emailext attachLog: true,
               body: "The Unit and Integration Test is successful.",
               subject: "Testing Stage Success",
               to: "jeetmane3801@gmail.com"    
       }
   }
       }
​       ​stage('Code Analysis') {
           steps {
               echo 'Analyzing code with SonarQube'
                echo 'mvn sonar:sonar'
           }
       }
       stage('Security Scan') {
           steps {
               echo 'Performing security scan with OWASP Dependency-Check'
                sh 'mvn org.owasp:dependency-check-maven:check'
           }
           post {
       success {
         
               emailext attachLog: true,
               body: "The Security scan is successfull.",
               subject: "Security Stage Success",
               to: "jeetmane3801@gmail.com"    
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
               echo 'Integration test running on staging server...'
           }
       }
        ​​stage('Deploy to Production') {
           steps {
               echo 'Deploying to AWS EC2 production server'
                echo 'ansible-playbook -i inventory/production deploy.yml'
           }
        }
    }
}