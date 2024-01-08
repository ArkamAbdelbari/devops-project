pipeline {
    agent any

    environment {
        // Définir les variables d'environnement, comme les identifiants du registre Docker
        DOCKER_REGISTRY = "monregistry.docker.com"
        DOCKER_CREDENTIALS_ID = "mes-identifiants-docker"
    }

    stages {
        stage('Initialisation') {
            steps {
                echo 'Initialisation...'
                // Initialiser des variables, préparer l'environnement, etc.
            }
        }
        stage('Build') {
            steps {
                echo 'Construction des images Docker...'
                // Construire l'image Docker pour le backend
                dir('backend') {
                    script {
                        docker.build("mon-backend:${env.BUILD_ID}")
                    }
                }
                // Construire l'image Docker pour le frontend
                dir('frontend') {
                    script {
                        docker.build("mon-frontend:${env.BUILD_ID}")
                    }
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Exécution des tests...'
                // Exécuter les tests pour le backend et/ou le frontend
                // Par exemple, des tests Maven pour le backend Java
                dir('backend') {
                    sh 'mvn clean test'
                }
                // Et/ou des tests pour votre frontend Angular
                dir('frontend') {
                    sh 'npm test'
                }
            }
        }
        stage('Docker Push') {
            steps {
                echo 'Poussée des images vers le registre Docker...'
                // Pousser l'image Docker du backend
                dir('backend') {
                    script {
                        docker.withRegistry("https://${DOCKER_REGISTRY}", DOCKER_CREDENTIALS_ID) {
                            docker.image("mon-backend:${env.BUILD_ID}").push()
                        }
                    }
                }
                // Pousser l'image Docker du frontend
                dir('frontend') {
                    script {
                        docker.withRegistry("https://${DOCKER_REGISTRY}", DOCKER_CREDENTIALS_ID) {
                            docker.image("mon-frontend:${env.BUILD_ID}").push()
                        }
                    }
                }
            }
        }
        stage('Clean Up') {
            steps {
                echo 'Nettoyage...'
                // Nettoyer après le build, par exemple, supprimer des fichiers temporaires
                sh 'echo "Nettoyage effectué"'
            }
        }
    }
    post {
        always {
            echo 'Le pipeline est terminé'
        }
    }
}
