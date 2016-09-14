package org.wavylabs

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

/**
  * Created by yuri.zelikov on 14/09/2016.
  */
object domain {

  case class CoinBalance(code: String, balance: Long)

  object JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
    implicit val coinBalanceFormat: RootJsonFormat[CoinBalance] = jsonFormat2(CoinBalance)
  }
}
