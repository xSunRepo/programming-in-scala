package book.chapter9

import java.io.{File, PrintWriter}
import java.util.Date

import util.DirectoryUtils

object ControlAbstraction extends App{
  def readFiles(dir: String): Array[File] = new File(dir).listFiles()
  // Currying
  def findFiles(dir:String)(pattern: File => Boolean) = {
    val files = readFiles(dir)
    for(file <- files if pattern(file)) yield file
  }

  val currentDir = DirectoryUtils.getCurrentDirectory(getClass)

  /*
  Scala lets us swap parenthesis with curly braces freely when there is exact one parameter;
  The purpose of it is to make it look like a built in control structure.
   */
  val scalaFiles = findFiles(currentDir) {
    _.getName.endsWith("scala")
  }.map(_.getName).mkString(", ")
  println(scalaFiles)

  // Loan Pattern
  // This example always ensure that the resource is closed, so the user don't have to worry about it.
  def withPrintWriter(file: String)(op: PrintWriter => Unit): Unit = {
    val printWriter = new PrintWriter(file)
    try {
      op(printWriter)
    } finally {
      printWriter.flush()
      printWriter.close()
    }
  }

  withPrintWriter(currentDir+File.separator+"time.txt") {
    _.print(new Date)
  }

  // By-name parameter method
  var enabledAssert = false
  /*
  Functionally, this is equivalent to def myAssert(predicate: () => Boolean), but semantically, we don't have to write the parameters as () => 3 < 5
  It evaluates predicate inside the function whereas def myAssert(predicate: Boolean) evaluate the predicate outside the function.
  */
  def myAssert(predicate: => Boolean): Unit = {
    if(enabledAssert && !predicate)
      throw new AssertionError()
  }

  // myAssert() will not throw exception in this case.
  myAssert(5/0 == 0)
}

