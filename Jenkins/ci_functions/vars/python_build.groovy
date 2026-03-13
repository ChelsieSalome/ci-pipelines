def call() {
      pipeline {
    agent { label 'python_agent' }

    stages {
      stage('Setup') {
        steps {
          sh "echo 'Running ${env.BUILD_ID} with workspace ${env.WORKSPACE}'"
          script {
            def foldersToDelete = ['test-reports', 'api-test-reports', 'venv']
            for (folder in foldersToDelete) {
              if (fileExists(folder)) {
                dir(folder) {
                  deleteDir()
                }
                echo "Deleted directory: ${folder}"
              } else {
                echo "Directory not found, skipping: ${folder}"
              }
            }
          }
          sh 'echo "Proving directories are empty:"'
          sh 'ls -d test-reports api-test-reports venv 2>/dev/null || echo "All 3 folders deleted"'
          sh 'python3 -m venv venv'
          sh 'venv/bin/pip install coverage pylint'
        }
      }

      stage('Build') {
        steps {
          sh 'venv/bin/pip install -r requirements.txt'
        }
      }

      stage('Python Lint') {
        steps {
          sh 'venv/bin/pylint --fail-under 5 *.py'
        }
      }

      stage('Test and Coverage') {
        steps {
          script {
            def testFiles = findFiles(glob: 'test*.py')
            for (file in testFiles) {
              sh "venv/bin/python -m coverage run --omit '*/site-packages/*,*/dist-packages/*' ${file.name}"
            }
            sh 'venv/bin/python -m coverage report'
          }
        }
        post {
          always {
            script {
              def test_reports_exist = fileExists 'test-reports'
              if (test_reports_exist) {
                junit 'test-reports/*.xml'
              }
              def api_test_reports_exist = fileExists 'api-test-reports'
              if (api_test_reports_exist) {
                junit 'api-test-reports/*.xml'
              }
            }
          }
        }
      }

      stage('Zip Artifacts') {
        steps {
          sh 'zip app.zip *.py'
          script {
            archiveArtifacts artifacts: 'app.zip',
                            allowEmptyArchive: true,
                            fingerprint: true,
                            onlyIfSuccessful: true
          }
        }
      }
    }
  }
}
