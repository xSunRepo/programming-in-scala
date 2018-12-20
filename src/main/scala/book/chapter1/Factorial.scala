package book.chapter1

/*
  This is one simple example that illustrates scala is a scalable language. Scala evolve and adapt base on its user demand.
  The example uses overridden mathematical operators of BigInt in the scala library to illustrate that scala is concise and precise.
  More importantly, the programmers are in control.
  On top of the BigInt class in there example, there are many more classes with operators overridden such as BigDecimal, Polynomial etc.
  Programmers can easily come up with classes that override these operators as well.

  Scala features:
    -object oriented
    -pure functional
    -fully compatible with java (compiles to java byte code)
    -higher order functions
    -statically typed with type inference
*/
object Factorial extends App {
  def factorialOf(num: BigInt): BigInt = {
    if(num == 0) 1 else num*factorialOf(num-1)
  }

  println(s"Factorial of 1: ${factorialOf(1)}")
  println(s"Factorial of 2: ${factorialOf(2)}")
  println(s"Factorial of 3: ${factorialOf(3)}")
  println(s"Factorial of 10: ${factorialOf(10)}")
  println(s"Factorial of 10: ${factorialOf(30)}")

}
