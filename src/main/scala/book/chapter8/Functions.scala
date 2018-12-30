package book.chapter8

import util.DirectoryUtils

import scala.io.Source

object Functions extends App{
  //Nesting and scope work on all scala constructs, including functions.
  def processFile(file: String, width: Int): Unit = {
    def processLine(line: String): Unit = {
      if(line.length > width)
        println(s"$file: ${line.length} $line")
    }
    for(line <- Source.fromFile(file).getLines()) {
      processLine(line)
    }
  }

  processFile(DirectoryUtils.getClassPath(getClass), 45)

  // Function and function values are like class to objects (class instances)
  def sum(x: Int, y: Int, z: Int): Int = x + y + z
  // The character _ is used as a placeholder
  val undefinedSum = sum _
  println(s"Value of undefinedSum(1, 1, 1): ${undefinedSum(1, 1, 1)}")
  // partial function
  val partiallyDefinedSum = sum(1, _, 3)
  println(s"Value of partiallyDefinedSum(2): ${partiallyDefinedSum(2)}")
  // Equivalent to def add = (x: Int, y: Int) => x + y
  // Equivalent to def add = (_: Int, _: Int) => _ + _
  val functionLiteral = (x: Int, y: Int) => x + y
  println(s"Value of function literal functionLiteral(2, 3): ${functionLiteral(2, 3)}")
  val add = (_: Int) + (_: Int)
  println(s"Value of function literal add(1, 1): ${add(1, 1)}")

  // Closed term: it has no free variable
  def addOne(x: Int) = x + 1

  // Closure
  var amount = 5
  // Open term: amount is the free variable
  def add(x: Int) = x + amount
  println(s"When amount is 5, add(1) produces ${add(1)}")
  amount = 10
  println(s"When amount is 10, add(1) produces ${add(1)}")

  def repeatedParameters(strs: String*): Unit = {
    strs.foreach(println)
  }
  val array = Array("one", "two", "three")
  // repeatedParamters doesn't take array directly
  repeatedParameters(array: _*)

  def defaultedParameters(default: String = "Hello") = println(default)
  defaultedParameters()
  defaultedParameters("Hi")

  // Often used together with defaulted parameters in scala
  def namedParameters(first: String, second: String) = {
    println(s"First: $first, second: $second")
  }

  namedParameters(second = "I'm passed in first", first = "I'm passed in second")

  // Scala optimizes tail recursion functions to while loops. Below example will print a single stack frame.
  def countDown(x: Int): Int =  {
    if(x == 0) {
      throw new IllegalStateException("Boom!")
    }
    else
      countDown(x-1)
  }

  countDown(5)
}
