pipeline {
    agent {
        label 'dummy'                       
    }
    stages {
        stage('Pull') {
            steps {
                echo "we are pulling from github"
                git "https://github.com/AnupDudhe/studentapp-ui"
            }
        }
        stage('Build') {
            steps {
                sh '''
                sudo apt install mvn clean
                sudo apt update
                sudo apt install unzip -y
                sudo curl -O https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.96/bin/apache-tomcat-9.0.96.zip
                sudo unzip apache-tomcat-9.0.96.zip
                echo we are building source code
                '''
            }
        }
        stage('Test') {
            steps {
                sh '''echo "we are tesing"
                '''
            }
        }
        stage('Deploy') {
            steps {
                sh '''
                sudo mv target/*.war  apache-tomcat-9.0.95/webapps/student.war
                sudo bash apache-tomcat-9.0.95/bin/catalina.sh start
                echo "we are deploying"
                '''
            }
        }
    }
}
