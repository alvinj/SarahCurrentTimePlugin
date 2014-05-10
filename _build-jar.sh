#!/bin/bash

sbt package

if [ $? != 0 ]
then
  echo "'sbt package' failed, exiting now"
  exit 1
fi

cp target/scala-2.10/currenttime_2.10-0.1.jar CurrentTime.jar

ls -l CurrentTime.jar

echo ""
echo "Created CurrentTime.jar. Copy that file to /Users/al/Sarah/plugins/DDCurrentTime, like this:"
echo "cp CurrentTime.jar /Users/al/Sarah/plugins/DDCurrentTime"

