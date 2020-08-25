node {
    stage('Init') {
        withCredentials([sshUserPrivateKey(credentialsId: 'jenkins-master-ssh-key1', keyFileVariable: 'SSHKEY', passphraseVariable: '', usernameVariable: 'SSHUSERNAME')]) {
            sh 'ssh -o StrictHostKeyChecking=no -i $SSHKEY root@104.131.70.93 yum install epel-release -y'
        }
    }
}