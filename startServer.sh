#!/bin/bash
#
# starts server

SERVER_ROOT=$0
STRLEN=${#SERVER_ROOT}
CMDLEN=14
SERVER_ROOT=${SERVER_ROOT:0:STRLEN-CMDLEN}


CLASSPATH=lib/iglu-1.0.2.jar
CLASSPATH=$CLASSPATH:lib/iglu-util-0.9.0.jar
CLASSPATH=$CLASSPATH:lib/iglu-common-0.9.1.jar
CLASSPATH=$CLASSPATH:lib/iglu-telnet-server-1.0.jar

CURRENT_PATH=$(PWD)

echo $CURRENT_PATH

cd $SERVER_ROOT
$JAVA_HOME/bin/java -classpath $CLASSPATH -Xms64m -Xmx256m org.ijsberg.iglu.samples.telnet.StandaloneServerConfiguration
cd $CURRENT_PATH

