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
                //bat 'testrunner -s WeatherSuite c:/AutomationProjects/Temp/RESTWeather.xml'
                bat 'testrunner -s WeatherSuite RESTWeather.xml'

            }
        }

        stage('Report'){
                    steps{
                        bat 'mvn site -DgenerateReports=false'
                        bat 'mvn surefire-report:report'

                    }
                }


    }
 }