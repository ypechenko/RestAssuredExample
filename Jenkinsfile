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

        post{
                    always{
                        bat 'mvn site -DgenerateReports=false'
                        bat 'mvn surefire-report:report'
                        publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true,
                        reportDir: 'target/site', reportFiles: 'surefire-report.html', reportName: 'Api Test Report', reportTitles: ''])



                    }
                }


    }
 }