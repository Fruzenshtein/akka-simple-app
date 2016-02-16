package com.actor

import akka.actor.ActorSystem

object Demo {
  def main(args: Array[String]): Unit = {

    val system = ActorSystem.create("file-reader")
    val scanner = system.actorOf(FolderScannerActor.props, "scanner")
    val directoryPath = getClass.getResource("/a-words").getPath

    scanner ! directoryPath

  }
}
