package com.actor

import java.io.File

import akka.actor.{PoisonPill, Props, Actor}
import akka.event.Logging

import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by Alex on 2/4/16.
  */
class FileReaderActor extends Actor {

  val log = Logging.getLogger(context.system, this)

  def receive = {
    case f: File => {
      log.info(s"Reading file ${f.getName}")
      var words = new ListBuffer[String]
      Source.fromFile(f).getLines().foreach(line => words += line )
      sender() ! words.toList
      self ! PoisonPill
    }
    case _ => log.info("Still waiting for a valid path")
  }

}

object FileReaderActor {

  def props = Props(new FileReaderActor)

}