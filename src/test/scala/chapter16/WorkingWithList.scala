package chapter16

import book.chapter16.ListExercise
import org.scalatest.{FlatSpec, Matchers}

class WorkingWithList extends FlatSpec with Matchers {
  val fruits = List("apple", "orange", "banana")
  val unsortedList = List(4, 1, 9, 10, 3, 2, 8, 6, 7, 5)
  val sortedList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  // List() and Nil has parameter type of Nothing which is a subtype of all types
  "List type in scala is covariant, so List[String]" must "be subtype of List[Object]" in {
    assert(fruits.isInstanceOf[List[Object]])
    val objList: List[Object] = fruits
    assert(objList.head.isInstanceOf[Object])
  }

  "ListExercise.insertionSort" should "sort a list of Int in ascending order" in {
    assert(ListExercise.insertionSort(unsortedList) == sortedList)
  }

  "Variable pattern match" can "work well with list" in {
    val List(a, b, c) = fruits
    assert(a == "apple")
    assert(b == "orange")
    assert(c == "banana")
  }

  // :: (cons) used in pattern match is actually a class
  "Constructor pattern match" can "work well with list too" in {
    val a :: rest = fruits
    assert(a == "apple")
    assert(rest == fruits.tail)
  }

  // First order function doesn't take any function as parameters

  "ListExercise.append" should "generate the same result as using :::" in {
    val a = List("apple")
    val b = List("orange", "banana")
    assert(a:::b == ListExercise.append(a, b))
  }

  /*
  Entire list needs to be travelled in order to get the length, so use isEmpty instead of length==0.
  It is important to organize data in list and use head/tail instead of last/init as the latter combination reverses
  the list during the operation which requires the traversal of the entire list.
   */

  "ListExercise.mergeSort" should "sort a list of Int in ascending order" in {
    assert(ListExercise.mergeSort((x: Int, y: Int) => x > y)(unsortedList) == sortedList)
  }

  // Higher order function takes function as parameters
  // foldLeft and foldRight are associated, so their results will be equivalent. However, their efficiency may not be the same.
  /*
  Library design principle: When designing a polymorphic method that takes some non-function arguments and a function argument,
  place the function argument last in a curried parameter list on its own.
  That way, the method's correct instance type can be inferred from the non-function arguments,
  and that type can in turn be used to type check the function argument.
   */
}
