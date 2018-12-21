package book.chapter2

object Basics extends App {
  /*
  Scala has two kinds of variables: var and val.
  Variables with var are mutable meaning their content can be changed within the same memory space,
  whereas val are immutable and cannot be reassigned.
   */
  var mutableVar = 1
  println(s"Newly created var: mutableVar has value of $mutableVar")
  mutableVar = 5
  println(s"mutableVar has been reassigned a value of $mutableVar")

  val immutableVar = 3

  while(mutableVar > 0) {
    println(mutableVar)
    mutableVar -= 1
  }

  /*
  Initialising array without the new key word; it's actually calling a function in the companion object.
  Even though the memory address allocated to array won't change in the below case, but the members in array can be easily altered.
   */
  val array = Array(1, 2, 4)
  array(2)=3
  println("array values:")
  array.foreach(println)

}
