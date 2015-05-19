# Pylon WebSocket Documentation
A Java WebSocket Server.

## Overview
The Pylon WebSocket server will be used as a nexus for controlling and receiving robot feedback.

## Installation
### Dependencies
[Java 7 or greater](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html), [Apache Tomcat 7 or greater](http://tomcat.apache.org/) and [Apache Maven](https://maven.apache.org/).
### Setup
Assuming all the necessary dependencies exist in your local development environment, this project is currently being built using Apache Maven with the Tomcat plugin for war deployment.  From the shell, simply enter:

```shell
mvn tomcat7:deploy
```
