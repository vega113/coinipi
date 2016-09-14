package org.wavylabs

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by yuri.zelikov on 14/09/2016.
  */
class BittrexApiTest extends FlatSpec with Matchers {
  trait Data {
    implicit val system = ActorSystem("test")
    implicit val materializer = ActorMaterializer()

    val api = new BittrexApiImpl {
      override implicit def actorSystem: ActorSystem = system

      override implicit def materializer: ActorMaterializer = materializer
    }
  }

  "BittrexApi" should "get coin balances" in new Data {
    Await.result(api.getBalances(), 100.millis) should be(defined)
  }
}
