package book.chapter7

import java.io.File

import util.DirectoryUtils

import scala.io.Source

object BuiltInControlStructures extends App{
  /*
  Scala only has four built in control structures: if, while, for and try.Others are achieved through libraries.
  Each of the control structures returns a value; while is a procedure and returns an instance of Unit: ().
   */
  def readFiles(directory: String): Array[File] = new File(directory).listFiles()
  def readLines(file: File): Iterator[String] = Source.fromFile(file).getLines()

  def grep(direcotry: String, pattern: String)= {
    for {
      file <- readFiles(direcotry)
      if (file.getName.endsWith("scala"))
      line <- readLines(file)
      trimmed = line.trim
      if (line.matches(pattern))
    } yield trimmed
  }
  grep(DirectoryUtils.getCurrentDirectory(getClass()), ".*for.*").foreach(println)

  /*
  Any exception that are subclass of java.lang.Error or RunTimeException are unchecked exception.
  Checked exceptions can be caught during compile time.
   */
  val uncheckedArithmeticException =
    try {
      10 / 0
    } catch {
      case e: ArithmeticException => 0
    }
  println(uncheckedArithmeticException)

  /*
  Scala try catch statement differs from java because values in the finally block doesn't override value in the try or catch block.
  Finally block is meant to ensure some side effects happen, such as cleaning up resources.
   */
  val one = try 1 finally 2
  def two: Int = try return 1 finally return 2
  println(two)

  /*
  Scala doesn't support continue and break in java;
  Continue can usually be replaced with if and break can usually be replaced with a boolean variable.

  Something similar to java break is implemented in scala;
  An exception is thrown in here by break and caught by the breakable block.
  breakable {
    while(...) {
      break;
    }
  }
   */

  def yieldByRow(row: Int, y: Int) = {
    val r = for(i <- 1 to y) yield {
      val prod = (i*row).toString
      val padding = " " * (4-prod.length)
      padding + prod
    }
    r.mkString
  }

  def multiplyTable(x: Int, y: Int) = {
    val table = for(i <- 1 to x) yield yieldByRow(i, y)
    table.mkString("\n")
  }

  println(multiplyTable(9, 9))

}
