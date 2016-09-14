package org.wavylabs

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import org.wavylabs.Boot.SystemConfig
import org.wavylabs.domain.CoinBalance

import scala.concurrent.{ExecutionException, Future}

/**
  * Created by yuri.zelikov on 14/09/2016.
  */
trait BittrexApi {

  def getBalances(): Future[List[CoinBalance]]
}

trait BittrexApiImpl extends BittrexApi with BittrexApiConfig with SystemConfig {

  implicit val executionContext = actorSystem.dispatcher

  override def getBalances(): Future[List[CoinBalance]] = {
    val resp = Http().singleRequest(HttpRequest(uri = s"${baseUrl()}/v1.1/account/getbalances?apikey=${apiKey}"))
    resp.foreach(x => println(x.entity.toString))
    resp.map(_ => List(CoinBalance("", 0)))
  }

}

trait BittrexApiConfig {
  def baseUrl() : String = "https://bittrex.com/api"
  def apiKey() : String = "125ca4d8c4d04073a92cca6cebfa268e"
  def apiSecret(): String = "1b57766060cc42c5876426583abac74f"
}
