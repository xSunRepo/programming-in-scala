package book.chapter4

import scala.collection.mutable

class ChecksumAccumulator {
  private var sum = 0
  /*
  Method parameter type needs to be specified as scala type inference doesn't work on method parameters.
  It is best practice to explicitly write the return type for public methods
  as code readers won't be able to infer return type as scala.
   */
  /*
  Below add method is a classic example for methods with side effects;
  taking an input and produces nothing, they are known as procedure.
   */
  def add(b: Byte): Unit = sum += b
  def checksum(): Int = ~(sum & 0xFF) + 1
}

/*
Companion object needs to be specified in the same file as the companion class. They can access each other's private members.
Companion object is implemented as a synthetic class through a static variable;
the name of the synthetic class is the object name follow by a $.
Companion object is lazy, meaning it is only initialized if it is accessed.
A companion object that doesn't have the same name as the companion class is called a standalone object.
 */
object ChecksumAccumulator extends App {
  val csa = new ChecksumAccumulator
  println(s"Initial checksum ${csa.checksum()}")
  for(i <- 1 to 5) {
    csa.add(i.toByte)
    println(s"Added $i to checksumAccumulator, checksum is ${csa.checksum()}")
  }

  val cache = mutable.Map[String,Int]()
  def calculate(str: String): Int = {
    if(cache.contains(str)) {
      println(s"Checksum found in cache for str: $str")
      cache(str)
    } else {
      val temp = new ChecksumAccumulator
      for(c <- str.toCharArray) {
        temp.add(c.toByte)
      }
      val cs = temp.checksum()
      cache += (str -> cs)
      cs
    }
  }

  Seq("Hello", "we", "are", "young", "Hello").foreach{
    str => println(s"String: $str: ${calculate(str)}")
  }

  /*
  Unlike java, scala lets the develop name the file something other than the class name.
  However, it's a good practice to name files after class as it would be easier for developers to find the desired files.
  Runnable script is suppose to end with an express.
   */

  /*
  scala built in command scalac can be used to compile scala files;
  fsc compiles faster than scalac by utilizing a daemon.
  fsc daemon can be turned off using fsc -shutdown
   */
}
