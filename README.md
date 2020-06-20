# Custom-Jenkins-Docker
Custom jenkins on docker which provides initial user creation and customisable plugins. This is a personal project to gather scripts together before instantiating jenkins.

## Structure
<pre>
    .
    ├── Dockerfile
    ├── README.md
    ├── build.sh
    ├── plugins.txt
    ├── scripts
    │   ├── gitscm.groovy
    │   └── security.groovy
    └── settings.xml
</pre>



## How to set master user and password
Have a loot at `scripts/security.groovy` file and update it as per your need.

## How to run
Execute the below command to build the docker locally and run it.

```
./build.sh
```
This may take few minutes to build and startup your brand new jenkins. Jenkins will be started on port 8080. Change `build.sh` to change it.

In browser open `localhost:8080` and enter the admin credentials. You can **ignore** setting up the plugins cause it's already setup. Just start using jenkins.

## Problems you may face while running docker commands in the build/deploy scripts
Docker client residing jenkins container may face issue while accessing `/var/run/docker.sock`. In order to fix this make sure to execute 
```
chmod 666 /var/run/docker.sock
```
Remember to restart both the host machine docker daemon and the jenkins container.
