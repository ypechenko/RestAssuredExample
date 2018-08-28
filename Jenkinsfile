pipeline{
    agent any
    stages{

        stage('Build'){
          steps{
               bat 'mvn clean compile'

           }

        }

        stage('Test'){
            steps{
                bat 'mvn -Dtest=apiTest01 test'
            }
        }

        stage('Report'){
                    steps{
                        bat 'mvn site -DgenerateReports=false'
                        bat 'mvn surefire-report:report'
                        junit '**/target/site/surefire-reports/*.html'


                    }
                }


    }
 }