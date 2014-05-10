package com.devdaily.sarah.plugin.currenttime

import com.devdaily.sarah.plugins.SarahPlugin
import java.util.Calendar
import java.text.SimpleDateFormat
import com.devdaily.sarah.Sarah
import com.devdaily.sarah.plugins.PleaseSay
import akka.actor.ActorSystem
import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class CurrentTimePlugin extends SarahPlugin {

    // nothing to do at startup
    def startPlugin = {}

    val phrasesICanHandle = List("what time is it")

    // used by any Future calls
    implicit val actorSystem = ActorSystem("CurrentTimeActorSystem")

    // sarah calls this automatically
    def textPhrasesICanHandle: List[String] = {
        return phrasesICanHandle
    }

    // handle our phrases
    def handlePhrase(phrase: String): Boolean = {
        if (phrasesICanHandle.contains(phrase)) {
            val currentTime = format("%s:%s %s\n", getCurrentHour, getCurrentMinute, getAmOrPm)
            val f = Future { brain ! PleaseSay("The current time is " + currentTime) }
            return true
        } else {
            return false
        }
    }

    override def setPluginDirectory(dir: String) {
        // do nothing 
    }

    // returns the current hour as a string
    def getCurrentHour: String = {
        val today = Calendar.getInstance().getTime()
        val hourFormat = new SimpleDateFormat("hh")
        try {
            // returns something like "01" if i just return at this point, so cast it to
            // an int, then back to a string (or return the leading '0' if you prefer)
            val currentHour = Integer.parseInt(hourFormat.format(today))
            return "" + currentHour
        } catch {
            // TODO return Some/None/Whatever
            case _: Throwable => return "0"
        }
        return hourFormat.format(today)
    }

    // returns the current minute as a string
    def getCurrentMinute: String = {
        val today = Calendar.getInstance().getTime()
        val minuteFormat = new SimpleDateFormat("mm")
        // in this case, returning "01" is okay
        return minuteFormat.format(today)
    }

    // returns "AM" or "PM"
    def getAmOrPm: String = {
        val today = Calendar.getInstance().getTime()
        val amPmFormat = new SimpleDateFormat("a")
        return amPmFormat.format(today)
    }

}

