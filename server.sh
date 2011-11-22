#!/bin/bash


SERVER_ROOT=$0
STRLEN=${#SERVER_ROOT}
CMDLEN=9
SERVER_ROOT=${SERVER_ROOT:0:STRLEN - CMDLEN}


case "$1" in

start)
    echo "starting server ..."
#    sudo -u not_root .$SERVER_ROOT/startapp.sh
	$SERVER_ROOT/startServer.sh&
	exit 0
    ;;
stop)
    echo "stopping server ..."
#    sudo -u not_root $SERVER_ROOT/invoke.sh core.ServerEnvironment.stop()
    $SERVER_ROOT/invoke.sh 'core.ServerEnvironment.stop()'
	exit 0
    ;;
restart)
    echo "restarting server ..."
    $SERVER_ROOT/invoke.sh 'core.ServerEnvironment.stop()'
	$SERVER_ROOT/startServer.sh&
	exit 0
    ;;
    
esac

echo "usage: ./server.sh stop|start|restart"

