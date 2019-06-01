package com.example

object TransferTool extends App {
  val jsonFilePath = args(0)
  val toolConfigure = new ToolConfigureImpl(jsonFilePath)
  val runner = new DbToDbToolRunner(toolConfigure)
  runner.run()
}
