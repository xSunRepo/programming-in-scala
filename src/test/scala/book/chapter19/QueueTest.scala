package book.chapter19

import book.chapter19.FunctionalQueue
import org.scalatest.{FlatSpec, Matchers}

class QueueTest extends FlatSpec with Matchers {
  val queue = FunctionalQueue(1, 2, 3)
  println(queue.head)
  println(queue.head)

}
