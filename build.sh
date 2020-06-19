#!/bin/bash
set -x

docker rm -f `docker ps -a -q`

docker build -t srv/jenkins . && \
	docker run \
	-d -p 8080:8080 \
	-p 50000:50000 \
	-v $HOME/jenkins_home:/var/jenkins_home \
	-v $HOME/.m2:/var/.m2 \
	-v /var/run/docker.sock:/var/run/docker.sock \
	--restart unless-stopped \
	--name my_jenkins srv/jenkins && \
	docker logs -f `docker ps -a -q`