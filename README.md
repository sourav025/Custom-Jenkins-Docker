# Custom-Jenkins-Docker
Custom jenkins on docker which provides initial user creation and customisable plugins

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

## How to run
Execute the below command to build the docker locally and run it.

```
./build.sh
```