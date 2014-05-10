
Sarah 'Current Time' Plugin
===========================

Add this plugin to Sarah so you can ask the current time.


Information
-----------

The way this currently works is that the "current time" phrase is in the
_sarah.gram_ file. That's not really good, that phrase should be moved to
this plugin. (It happened that way because I used to handle this with
AppleScript, then decided to handle it better as a plugin.)


Files
-----

The jar file built by this project needs to be copied to the Sarah plugins directory.
On my computer that directory is _/Users/al/Sarah/plugins/DDCurrentTime_.

Files in that directory should be:

    CurrentTime.info
    CurrentTime.jar
    README.txt

The _CurrentTime.info_ file currently contains these contents:

    main_class = com.devdaily.sarah.plugin.currenttime.CurrentTimePlugin
    plugin_name = Current Time


To-Do
-----

* Move the "What time is it?" phrase to this plugin.
* Then let this plugin handle multiple phrases, such as "Current time",
  "What time is it?", "Sarah, what time is it?", and so on.

I also need to improve the Sarah2 jar-building process, because this plugin and all
other plugins are dependent on that jar, but that's more of a Sarah2 "to do" than 
anything that needs to be done here. 


Developers - Building this Plugin
---------------------------------

You can build this plugin using the shell script named _build-jar.sh. It currently looks like this:

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


Dependencies
------------

This plugin depends on:

* The Sarah2.jar file.
* The Akka/Scala actors. The actor version needs to be kept in sync with whatever actor version
  Sarah2 uses.

As mentioned above, I need to improve the process of requiring and using the Sarah2.jar file,
but that's more of a problem for the Sarah2 project than for this project. 









