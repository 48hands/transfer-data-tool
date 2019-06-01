package com.example


trait ToolConfigure {
  def appName: String
  def inputTableName: String
  def outputTableName: String
  def targetColumns: Set[String]
  def canOverride: Boolean
}

class ToolConfigureImpl(jsonFilePath: String) extends ToolConfigure {
  import spray.json._

  private[this] lazy val settings = {
    import SettingsJsonProtocol._
    val source = scala.io.Source.fromFile(jsonFilePath)
    source.mkString.parseJson.convertTo[Settings]
  }

  override def appName: String = settings.appName
  override def inputTableName: String = {
    val source = scala.io.Source.fromFile(settings.input.sqlQueryFilePath)
    val query = source.mkString.replace(";","")
    s"($query) as tbl"
  }

  override def outputTableName: String = settings.output.tableName
  override def targetColumns: Set[String] = settings.output.targetColumns
  override def canOverride: Boolean = false
}
