package org.wavylabs


import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
import akka.util.Timeout
import org.wavylabs.domain.JsonSupport._
import org.wavylabs.domain._
import spray.json._

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

/**
  * Created by yuri.zelikov on 14/09/2016.
  */
object Boot extends App {

  trait SystemConfig {
    implicit def actorSystem: ActorSystem
    implicit def materializer: ActorMaterializer
  }


  implicit val system = ActorSystem("boot")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  object SystemBindings extends SystemConfig {
    lazy val actorSystem: ActorSystem = system
    lazy val materializer: ActorMaterializer = materializer
  }

  val route =
    path("balances") {
      get {
        complete {
          Future {
            HttpEntity(ContentTypes.`text/html(UTF-8)`,
              s"""
                 |<h1>Crypto coin balances</h1>
            """.stripMargin)
          }
        }
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
  bindingFuture.onComplete {
    case Success(s) =>
      println(s"Server online, stats are available at http://localhost:8080/stats")
    case Failure(t) =>
      System.err.println("Failed to run the server: " + t.getMessage)
      system.terminate().onComplete(_ => System.exit(1))
  }
}
