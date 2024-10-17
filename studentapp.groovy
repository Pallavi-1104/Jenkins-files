pipeline {
    agent {
        label 'dummy'                       
    }
    stages {
        stage('Pull') {
            steps {
                echo "We are pulling from GitHub"
                git "https://github.com/AnupDudhe/studentapp-ui"
            }
        }
        stage('Build') {
            steps {
                sh '''
                sudo apt update
                sudo apt install maven -y
                mvn clean install package
                sudo apt install unzip -y
                sudo curl -O https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.96/bin/apache-tomcat-9.0.96.zip
                sudo unzip -o apache-tomcat-9.0.96.zip
                echo "We are building the source code"
                '''
            }
        }
        stage('Test') {
            steps {
                sh '''echo "We are testing"
                '''
            }
        }
        stage('Deploy') {
            steps {
                sh '''
                sudo mv target/*.war apache-tomcat-9.0.96/webapps/student.war
                sudo bash apache-tomcat-9.0.96/bin/catalina.sh start
                echo "We are deploying"
                '''
            }
        }
    }
}

