package com.example

import java.util.Properties

import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

trait ToolRunner {
  def run(): Unit
  def read: DataFrame
  def select(df: DataFrame): DataFrame
  def write(df: DataFrame): Unit
}

class DbToDbToolRunner(toolConfigure: ToolConfigure) extends ToolRunner {
  private[this] val dbconf = ConfigFactory.load().getConfig("db")
  private[this] val inDbConf = dbconf.getConfig("input")
  private[this] val outDbConf = dbconf.getConfig("output")

  private[this] val spark =
    SparkSession.builder()
      .master("local[1]")
      .appName(toolConfigure.appName)
      .getOrCreate()

  override def run(): Unit = {
    val df = read()
    write(select(df))
  }

  override def read(): DataFrame = {
    val connectionProperties = new Properties()
    connectionProperties.setProperty("user", inDbConf.getString("pgUser"))
    connectionProperties.setProperty("password", inDbConf.getString("pgPass"))
    spark.read.jdbc(inDbConf.getString("pgUrl"), toolConfigure.inputTableName, connectionProperties)
  }

  override def select(df: DataFrame): DataFrame = {
    val columns = toolConfigure.targetColumns.toSeq
    if (columns.mkString == "*") df
    else df.selectExpr(columns:_*)
  }

  override def write(df: DataFrame): Unit = {
    val connectionProperties = new Properties()
    connectionProperties.setProperty("user",  outDbConf.getString("pgUser"))
    connectionProperties.setProperty("password",  outDbConf.getString("pgUser"))
    val saveMode = if (toolConfigure.canOverride) SaveMode.Overwrite else SaveMode.Append

    df.write.mode(saveMode)
      .jdbc(outDbConf.getString("pgUrl"), toolConfigure.outputTableName, connectionProperties)
  }
}
