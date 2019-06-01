package com.example

object TransferToolTest extends App {
  val toolConfigure = TestToolConfigure("testApp", "users", "test_users", Set("*"), true)
  val testRunner = new DbToDbToolRunner(toolConfigure)

  testRunner.read().show()
  testRunner.run()
}


case class TestToolConfigure(appName: String, inputTableName: String,
                             outputTableName: String, targetColumns: Set[String],
                             canOverride: Boolean) extends ToolConfigure
