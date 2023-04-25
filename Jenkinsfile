node {
  stage('SCM') {
    checkout scm
  }
  withSonarQubeEnv('SonarQube') {
  // Provide SonarQube username and password
  sh 'sonar-scanner -Dsonar.login=shreya -Dsonar.password=123456'
}

}