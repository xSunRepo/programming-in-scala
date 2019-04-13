package book.chapter17

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable

class CollectionLibrary extends FlatSpec with Matchers {
  /*
   As the name of the trait Seq[T] implies, it's a data collection that keeps data in order.
   Common data collections such as List, Array, ListBuffer, ArrayBuffer etc all implements Seq[T].
  */

  /*
  Appending to list is inefficient, so a common approach is to prepend to the list then reverse it.
  An alternative is to use ListBuffer which costs linear time to prepend and append.
  ListBuffer can also be used with a while loop to prevent possible stack overflow that can be caused by
  a non tail recursive algorithm.
  */
  "multable.ListBuffer" should "let us append without sacrificing performance" in {
    val listBuffer = mutable.ListBuffer[Int]()
    listBuffer += 2
    listBuffer ++= List(3, 4)
    1 +=: listBuffer
    List(0) ++=: listBuffer
    listBuffer -= 4
    assert(listBuffer.toList == List(0, 1, 2, 3))
  }

  "multable.ArrayBuffer" should "let us construct array without length" in {
    val arrayBuffer = mutable.ArrayBuffer[Int]()
    arrayBuffer += 1
    arrayBuffer(0) = 0
    arrayBuffer += 1
    arrayBuffer ++= Array(2, 3)
    arrayBuffer ++= List(4, 5)
    arrayBuffer -= 0
    val array = arrayBuffer.toArray
    assert(array(0) == 1)
  }

  "Implict StringOp type" should "lets us use String as sequence" in {
    val str = "zzzTreat me like a sequence!zzz"
    assert(str.filter(_=='z') == "zzzzzz")
  }

  /*
  Scala has singleton objects for Set and Map that has a size that's less than 5 for better performance.
   */
  "mutable.Map and immutable.Map" should "be able to convert to each other" in {
    val mutableMap = mutable.Map[String, String]()
    mutableMap("Apple") = "Red"
    mutableMap += ("Banana" -> "Yellow")
    val immutableMap = Map.empty[String, String] ++ mutableMap
    val diff = mutableMap.toSet diff immutableMap.toSet
    assert(diff.isEmpty)
  }

  "TreeSet and TreeMap" should "maintain order" in {
    val treeSet = mutable.TreeSet(3, 1, 4, 2, 5)
    assert(treeSet.toList == List(1, 2, 3, 4, 5))
  }
}
