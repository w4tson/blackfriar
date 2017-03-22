[![Build Status](https://travis-ci.org/w4tson/blackfriar.svg?branch=master)](https://travis-ci.org/w4tson/blackfriar)

Example Java app written in Spring Boot

# Getting Started

 * `docker-compose up -d`
 * `./gradlew clean bootRun`
 
# Build docker image

 * `./gradlew buildDockerImage`
 
#### MacOS workaround

 Little workaround for talking to an unsecured docker daemon on MacOS

 * `brew install socat`
 * `socat TCP-LISTEN:2375,range=127.0.0.1/32,reuseaddr,fork UNIX:/var/run/docker.sock`

# Features

 *  Spring HATEOAS
 *  JPA + OracleDB
 *  DB migrations with flyway
 *  RabbitMQ integration


# Todo

 * ~~packageJavadoc~~ 
 * ~~packageSources~~
 * jcenter?
 * spring security + docker ldap
 * consider webpack/gulp
 * Pre-Process Jinja2 config
 * Ansible
 * ~~RPM packaging~~
 * Hibernate interceptors
 * Integration tests
 * Advanced JPA queries
 * Custom developer gradle tasks for sending AMQP messages
 * ~~Gradle release plugin~~
 * ReactJS + Redux UI
 * Test jar. Instead of spaghetti junction dependencies 
 * Look into leveraging the 'java-library' plugin in gradle   
 * assertJ 
 * Readme instructions on how to run 
 * ~~upgrade to SpringBoot 1.5~~
	
