FROM jenkins/jenkins:lts
MAINTAINER sourav025

# Derived from https://github.com/getintodevops/jenkins-withdocker (miiro@getintodevops.com)

USER root

# Install the latest Docker CE binaries and add user `jenkins` to the docker group
RUN apt-get update && \
    apt-get -y --no-install-recommends -qq install apt-transport-https \
      ca-certificates \
      curl \
      gnupg2 \
      software-properties-common \
      maven && \
    curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg > /tmp/dkey; apt-key add /tmp/dkey && \
    add-apt-repository \
      "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") \
      $(lsb_release -cs) \
      stable" && \
   apt-get -qq update && \
   apt-get -y -qq --no-install-recommends install docker-ce && \
   apt-get clean && \
   usermod -aG docker jenkins

#VOLUME /var/run/docker.sock
#RUN chmod 666 /var/run/docker.sock

ENV MAVEN_HOME /usr/share/maven
COPY settings.xml /etc/maven/settings.xml

COPY scripts/* /usr/share/jenkins/ref/init.groovy.d/

# Install necessary plugins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# drop back to the regular jenkins user - good practice
USER jenkins
