package com.example

import spray.json.DefaultJsonProtocol

case class Input(sqlQueryFilePath: String)

case class Output(tableName: String, targetColumns: Set[String])

case class Settings(appName: String, input: Input, output: Output)

object SettingsJsonProtocol extends DefaultJsonProtocol {
  implicit val inputFormat = jsonFormat1(Input)
  implicit val outputFormat = jsonFormat2(Output)
  implicit val settingsFormat = jsonFormat3(Settings)
}
