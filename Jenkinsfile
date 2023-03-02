pipeline{
agent any
    tools{
    maven "Maven"
    }
    
stages
    {
    stage("code checkout"){
        steps{
        bat "echo hello stage"
        }
    }
    stage("code clean"){
        steps{
        bat "mvn clean"
        }
    }
       stage("code compile"){
        steps{
        bat "mvn compile"
        }
    }
    stage("code test"){
        steps{
           bat "mvn test"}
        }
    stage ("Sonar Analysis")
    {
        steps{
            withSonarQubeEnv("SonarQubeServerDevTestOps")
            {
                bat "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184:sonar"
            }
        }
    }
        stage("Upload to Artifactory"){
            steps{
            rtMavenDeployer(id: 'deployer', 
                            serverId: '123456789@artifactory', 
                            releaseRepo: 'eStoreArtifactoryPractise',
                            snapshotRepo: 'eStoreArtifactoryPractise')
                rtMavenRun(pom:'pom.xml',
                          goals: 'clean install',
                          deployerId: 'deployer')
                rtPublishBuildInfo(serverId: '123456789@artifactory')
            }
        }
    }
    post{
        success{
        bat "echo success"
        }
    }
}
