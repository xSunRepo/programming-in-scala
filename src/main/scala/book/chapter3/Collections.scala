package book.chapter3

object Collections {
  /*
  Unlike array, list members are also immutable. Every time an element is added or removed from a list, a new list will be created.
  New elements should always prepend to the list as time complexity to append to the list increases linearly.
   */
  val list = List(1, 2, 3)
  val composedList = 3 :: 2 :: 1 :: Nil

  val alteredList = 5 :: 4 :: composedList
  println("list values:")
  alteredList.foreach(println)

  /*
  The tuple in the example is a tuple of two, but there could be tuple of 3, 4, 5 and more..
  The reason tuple cannot be accessed like a list is because each member in the tuple could have different types.
   */
  val tuple = ("hello", 123)
  println(s"First member of tuple: ${tuple._1}, second member of the same tuple: ${tuple._2}")

  /*
  Set and map are pretty much the same as in other programming languages. Scala have both mutable and immutable versions
  of set and map through traits in different packages.
  scala.collection.mutable and scala.collection.immutable
  Scala uses mutable by default.
   */
  val set = Set("A", "B", "C")
  val map = Map(
    1 -> "hello",
    2 -> "hi",
    "howdy" -> 3
  )
}
