#!/bin/bash
# invokes commands like the one below
# ./invoke.sh cluster.module.invokeMethod("jeroen@ijsberg.nl","my subject","some contents")

SERVER_ROOT=$0
STRLEN=${#SERVER_ROOT}
CMDLEN=10
SERVER_ROOT=${SERVER_ROOT:0:$STRLEN - CMDLEN}

rm -f $SERVER_ROOT/console/output.txt
echo $* >> $SERVER_ROOT/console/input.txt
let i=0
while [ ! -f $SERVER_ROOT/console/output.txt ]
do
   #wait .01 of a second to avoid CPU consumption
   sleep .01
   let i=i+1
   #stop waiting after 10 seconds
   if [ $i -gt 1000 ]
   then
       echo "command timed out"
       exit 1
   fi
done
#display message
cat $SERVER_ROOT/console/output.txt
