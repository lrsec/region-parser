#!/usr/bin/env bash
./gradlew clean build
java  -Xdebug -Xrunjdwp:transport=dt_socket,suspend=n,server=y,address=5011 -jar build/libs/region-parser-0.0.1-SNAPSHOT.jar
