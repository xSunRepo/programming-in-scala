package chapter14

import book.chapter10.Element
import org.scalatest.{FlatSpec, Matchers}

/*
Learn more from here: http://www.scalatest.org/user_guide
 */

class Testing extends FlatSpec with Matchers{
  "An empty set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  it should "produce NoSuchElementException when head is inovked" in {
    assertThrows[NoSuchElementException](Set.empty.head)
  }

  "An UniformElement" should "have width equal to the passed in value" in {
    val uniformElement = Element('x', 2, 3)
    assertResult(2)(uniformElement.width)
  }

  it should "have height equal to the passed in value" in {
    val uniformElement = Element('x', 2, 3)
    uniformElement.height should be (3)
  }
}
