#!groovy
 
import jenkins.model.*
import hudson.security.*
import jenkins.security.s2m.AdminWhitelistRule
import jenkins.model.JenkinsLocationConfiguration
import net.sf.json.JSONObject

import jenkins.security.QueueItemAuthenticatorConfiguration
import org.jenkinsci.plugins.authorizeproject.GlobalQueueItemAuthenticator
import org.jenkinsci.plugins.authorizeproject.strategy.TriggeringUsersAuthorizationStrategy

println('=== Configuring users')

def instance = Jenkins.getInstance()
String adminEmail = 'jenkins@souravs.me'
JenkinsLocationConfiguration location = instance.getExtensionList('jenkins.model.JenkinsLocationConfiguration')[0]
 
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount('admin', 'pass1234')
instance.setSecurityRealm(hudsonRealm)
 
def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
instance.setAuthorizationStrategy(strategy)
 
Jenkins.instance.getInjector().getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false)
println "Updating Jenkins Email to: ${adminEmail}"
location.adminAddress = adminEmail

location.save()
instance.save()


println '=== Configure Authorize Project'
GlobalQueueItemAuthenticator auth = new GlobalQueueItemAuthenticator(
    new TriggeringUsersAuthorizationStrategy()
)
QueueItemAuthenticatorConfiguration.get().authenticators.add(auth)
