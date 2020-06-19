#!groovy

// imports
import jenkins.model.Jenkins

// parameters
def gitParameters = [
  globalConfigEmail:  'jenkins@souravs.me',
  globalConfigName:   'Jenkins CI'
]

// get Jenkins instance
Jenkins jenkins = Jenkins.getInstance()

// get Git plugin
jenkinsGitConfig = jenkins.getDescriptor("hudson.plugins.git.GitSCM")

// set Git plugin parameters
jenkinsGitConfig.setGlobalConfigName(gitParameters.globalConfigName)
jenkinsGitConfig.setGlobalConfigEmail(gitParameters.globalConfigEmail)

// save current Jenkins state to disk
jenkinsGitConfig.save()
jenkins.save()