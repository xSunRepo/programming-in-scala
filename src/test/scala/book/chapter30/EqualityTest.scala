package book.chapter30

import book.chapter30.Equality.{ColoredPoint, Colors, Point}
import org.scalatest.{FlatSpec, Matchers}

class EqualityTest extends FlatSpec with Matchers {
  val point = new Point(1, 2)
  val anonymousPoint = new Point(1, 1) {
    override val y: Int = 2 }
  val redPoint = new ColoredPoint(1, 2, Colors.Red)

  val pointSet = Set(point)

  "Original point" should "be equivalent to the anonymous point" in {
    assert(point == anonymousPoint)
    assert(anonymousPoint == point)
    assert(pointSet.contains(anonymousPoint))
  }

  it should "not be equivalent to the colored point" in {
    assert(point != redPoint)
    assert(redPoint != point)
    assert(!pointSet.contains(redPoint))
  }


}
