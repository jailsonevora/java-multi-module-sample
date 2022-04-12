Root project composed by several modules

***Resource***
markdown rules
https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet

***New project schema ***

This is our new project schema, composed by one root solution and 3 more modules.

![](images/ine-project.PNG?raw=true)

Each modules has   its own pom file, but the major one is the pom of the root project.

![](images/ine-project-files.PNG?raw=true)

The "common" module is build as jar, because it is a class library project and the other modules "sge" and "universe" are packaged as war. This come from these being a web project. They are completely independent from each other.

![](images/ine-project-packge.PNG?raw=true)

To reuse some code, we create the module "common" to join in one place, classes and methods that are needed both in "sge" and in "universe". These classes are just generics like some common interface, dto, exception or error handler.

![](images/ine-project-sge-common-dependency.PNG?raw=true)

These dependencies are declared in each child pom file.

![](images/ine-project-universe-common-dependency.PNG?raw=true)

---

***Swagger for SGE***
http://127.0.0.1:8080/swagger-ui.html

***Swagger for Universe***
http://127.0.0.1:8081/swagger-ui.html

---
As sake of handling communication between "sge" and "universe", we will need to install kafka. For who do not know, Kafka is a fast and scalable messaging queue, capable of handling heavy loads in context of read and write. You can find more about Kafka on http://kafka.apache.org/. Apache Kafka requires a running Zookeeper instance, which is used for reliable distributed coordination.

***Installation Kafka/zookeeper***
Download Zookeeper
We suggest downloading the current stable release
http://www-us.apache.org/dist/zookeeper/

Download Kafka
We suggest downloading the current stable release
http://kafka.apache.org/downloads.html
http://www-eu.apache.org/dist/kafka/2.0.0/kafka-2.0.0-src.tgz

Installation of Kafka and Zookeeper on Windows
https://dzone.com/articles/running-apache-kafka-on-windows-os
and if you prefer in docker container use this tutorial https://www.e4developer.com/2018/05/20/how-to-easily-run-kafka-with-docker-for-development/ 

A comma separated list of directories under which to store log files
C:\kafka\config\server.properties
log.dirs=C:/kafka/kafka-logs

the directory where the snapshot is stored.
do not use /tmp for storage, /tmp here is just 
example sakes.
C:\zookeeper\conf\zoo.config
dataDir=C:/zookeeper/data

***Running Kafka/zookeeper***
run zookeeper 
cd C:\zookeeper\bin
zkServer.cmd
 
run kafka
cd C:\kafka
.\bin\windows\kafka-server-start.bat .\config\server.properties


***Creating topics***
https://kafka.apache.org/quickstart
https://kafka.apache.org/documentation/

Let's create a topic named "test" with a single partition and only one replica:

1 > bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
We can now see that topic if we run the list topic command:

1 > bin/kafka-topics.sh --list --zookeeper localhost:2181

*On windows change .sh to .bat prefix kafka-topics.sh to kafka-topics.bat and enter to bin/windows folder*

2 > Alternatively, instead of manually creating topics you can also configure your brokers to auto-create topics when a non-existent topic is published to.


***Send some messages***

Kafka comes with a command line client that will take input from a file or from standard input and send it out as messages to the Kafka cluster. By default, each line will be sent as a separate message.

Run the producer and then type a few messages into the console to send to the server.

1 > bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test

*On windows change .sh to .bat prefix kafka-console-producer.sh to kafka-console-producer.bat and enter to bin/windows folder*

***Start a consumer***

Kafka also has a command line consumer that will dump out messages to standard output.

1 > bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning

*On windows change .sh to .bat prefix kafka-console-consumer.sh to kafka-console-consumer.bat and enter to bin/windows folder*

If you have each of the above commands running in a different terminal then you should now be able to type messages into the producer terminal and see them appear in the consumer terminal.

All of the command line tools have additional options; running the command with no arguments will display usage information documenting them in more detail.

***Kafka config in application.properties***

Finally, we need to add to application property

*spring.kafka.bootstrap-servers=localhost:9092*
*spring.kafka.consumer.group-id=ine-group*

***Kafka Arch***

![](images/kafka-components.png?raw=true)

***Integration of Kafka with Solution***

![](images/kafka-integration.png?raw=true)

that is all folks
---
