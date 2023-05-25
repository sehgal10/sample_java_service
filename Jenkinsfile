@NonCPS
def cancelPreviousBuilds() {
    def jobName = env.JOB_NAME
    def buildNumber = env.BUILD_NUMBER.toInteger()

    /* Get job name */
    def currentJob = Jenkins.instance.getItemByFullName(jobName)

    /* Iterating over the builds for specific job */
    for (def build : currentJob.builds) {
        def listener = build.getListener()
        def exec = build.getExecutor()
        /* If there is a build that is currently running and it's not current build */
        if (build.isBuilding() && build.number.toInteger() < buildNumber && exec != null) {
            /* Then stop it */
            exec.interrupt(
                    Result.ABORTED,
                    new CauseOfInterruption.UserInterruption("Aborted by #${currentBuild.number}")
                )
            println("Aborted previously running build #${build.number}")
        }
    }
}

void setBuildStatus(String message, String state) {
	step([
		$class: "GitHubCommitStatusSetter",
		commitShaSource: [$class: "BuildDataRevisionShaSource"],
		reposSource: [$class: "AnyDefinedRepositorySource"],
		reposSource: [$class: "ManuallyEnteredRepositorySource", url: "git@github.com:sukhwinder/org-sample_service-eth.git"],
		contextSource: [$class: "DefaultCommitContextSource"],
		errorHandlers: [[$class: "ShallowAnyErrorHandler"]],
		statusResultSource: [$class: "DefaultStatusResultSource"],
		statusBackrefSource: [$class: "BuildRefBackrefSource"]
	]);
}

void pullRepository(String branch) {
	checkout(
		[
			$class: 'GitSCM',
			branches: [
				[
					name: branch
				]
			],
			doGenerateSubmoduleConfigurations: false,
			extensions: [
				[
					$class: 'SubmoduleOption',
					disableSubmodules: false,
					parentCredentials: true,
					recursiveSubmodules: false,
					reference: '',
					trackingSubmodules: false
				]
			],
			submoduleCfg: [],
			userRemoteConfigs: [
                [
                    credentialsId: 'ssh',
                    url: 'git@github.com:sukhwinder/org-sample_service-eth.git',
                    name: '',
                    refspec: ''
                ],
            ]
		]
	);
}

String detectOperatingSystemType(){

 String osname = System.getProperty("os.name");
    if (osname.startsWith("Windows"))
        return "win";
    else if (osname.startsWith("Mac")){
	    System.out.println("OS IS MACINTOSH");
        return "mac";
	}
    else if (osname.contains("nux"))
        return "nux";
	else {
	    System.out.println("OS is neither Win/Linux/Mac");
	    return null;
	}
}

String detectJavaHome(int version){

	String osname = System.getProperty("os.name");

	String jdk = "";
	if (version == 8) {
	    jdk = "jdk1.8.0_202";
	} else {
	    jdk = "jdk-11.0.16.1"
	}

	if (osname.startsWith("Windows")) {
		return "-Dorg.gradle.java.home=\"C:/Program Files/Java/" + jdk + "\"";
	} else if (osname.startsWith("Mac")) {
		return "";
	} else if (osname.contains("nux")) {
		return "";
	} else {
		System.out.println("OS is neither Win/Linux/Mac");
		return null;
	}
}

pipeline {

	options {
         disableConcurrentBuilds()
         timeout(time: 6, unit: 'HOURS')
    }

    agent any

    tools {
        gradle "gradle-7.5.1"
        maven "maven-3.6.3"
    }

    triggers {
        githubPush()
    }

	stages {

        stage('setup-build') {
            steps {
                script {
                    cancelPreviousBuilds();
                }
            }
        }

		stage('checkout') {
            steps{
                echo 'pulling repository'
                pullRepository(env.BRANCH_NAME)

                script {
                    String osType = detectOperatingSystemType();
                }
            }
        }

        // Building JAR
        stage ('setup') {
            steps {
                script {
                    sh """
                    """
                }
            }
        }

        // Running tests
         stage ('test') {
             steps {
                script {
                    String javaHome = detectJavaHome(11);
                    sh """
                    """
                 }
             }
         }

        // Building JAR
        stage ('build') {
            steps {
                script {
                    String javaHome = detectJavaHome(11);
                    sh """
                        gradle clean jar ${javaHome}
                    """
                }
            }
        }

        stage ('docker-clean') {
            steps {
                script {
                    String javaHome = detectJavaHome(11);

                    lock('docker') {

                        echo 'Building docker image for latest...'
                        sh """
                            docker system prune -af
                        """
                    }
                }
            }
        }

		stage ('docker-build-latest') {
            steps {
                script {

                    lock('docker') {

                        String osType = detectOperatingSystemType();
                        String javaHome = detectJavaHome(11);

                        if (osType == "win") {

                            bat """
                                gradle dockerBuildImage -Pbranch=${env.BRANCH_NAME} -Pcommit=latest ${javaHome}
                            """
                            bat """
                                gradle dockerPushImage -Pbranch=${env.BRANCH_NAME} -Pcommit=latest ${javaHome}
                            """

                        } else if (osType == "mac" || osType == "nux") {

                            sh """
                                gradle dockerBuildImage -Pbranch=${env.BRANCH_NAME} -Pcommit=latest ${javaHome}
                            """
                            sh """
                                gradle dockerPushImage -Pbranch=${env.BRANCH_NAME} -Pcommit=latest ${javaHome}
                            """

                        }
                    }
                }
            }
        }

        stage ('docker-build-commit') {
            steps {
                script {

                    lock('docker') {

                        String osType = detectOperatingSystemType();
                        String javaHome = detectJavaHome(11);

                        if (osType == "win") {

                            bat """
                                gradle dockerBuildImage -Pbranch=${env.BRANCH_NAME} -Pcommit=${env.GIT_COMMIT} ${javaHome}
                            """
                            bat """
                                gradle dockerPushImage -Pbranch=${env.BRANCH_NAME} -Pcommit=${env.GIT_COMMIT} ${javaHome}
                            """

                        } else if (osType == "mac" || osType == "nux") {

                            sh """
                                gradle dockerBuildImage -Pbranch=${env.BRANCH_NAME} -Pcommit=${env.GIT_COMMIT} ${javaHome}
                            """
                            sh """
                                gradle dockerPushImage -Pbranch=${env.BRANCH_NAME} -Pcommit=${env.GIT_COMMIT} ${javaHome}
                            """

                        }
                    }
                }
            }
        }
    }

	post {
		failure {
			echo 'Setting commit status failure'
			setBuildStatus("Build failed", "FAILURE");
		}
		success {
			echo 'Setting commit status success'
			setBuildStatus("Build success", "SUCCESS");
		}
	}
}