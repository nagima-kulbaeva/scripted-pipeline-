properties([
    parameters([
        string(defaultValue: '', description: 'Please enter VM IP', name: 'nodeIP', trim: true)
        ])
    ])
if (nodeIP.length() > 6) {
    node {
        stage('Pull Repo') {
            git branch: 'master', changelog: false, poll: false, url: 'https://github.com/ikambarov/ansible-Flaskex.git'
        }
        withEnv(['ANSIBLE_HOST_KEY_CHECKING=False', 'FLASKEX_REPO=https://github.com/ikambarov/Flaskex.git', 'FLASKEX_BRANCH=master']) {
            stage("Install FLaskex"){
                ansiblePlaybook credentialsId: 'jenkins-master-ssh-key1', inventory: '${nodeIP},', playbook: 'main.yml'
                }
            }  
        }  
}
else {
    error 'Please enter valid IP address'
}