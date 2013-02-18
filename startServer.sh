#!/bin/bash
#
# starts server

SERVER_ROOT=$0
STRLEN=${#SERVER_ROOT}
CMDLEN=14
SERVER_ROOT=${SERVER_ROOT:0:STRLEN-CMDLEN}

CLASSPATH=lib/iglu-1.0.3.jar
CLASSPATH=$CLASSPATH:lib/iglu-common-0.9.2.jar
CLASSPATH=$CLASSPATH:lib/iglu-util-0.9.1.jar

CURRENT_PATH=$PWD

cd $SERVER_ROOT
$JAVA_HOME/bin/java -classpath $CLASSPATH -Xms64m -Xmx256m org.ijsberg.iglu.configuration.module.ServerEnvironment org.ijsberg.iglu.samples.http.IJsbergWebsiteAssembly -xcl lib
cd $CURRENT_PATH

