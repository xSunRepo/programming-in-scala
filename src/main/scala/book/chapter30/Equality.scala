package book.chapter30

// Using object as module
object Equality {
  class Point(val x: Int, val y: Int) {
    override def equals(obj: Any): Boolean = {
      obj match {
        case that: Point =>
          (that canEqual this) && this.x == that.x && this.y == that.y
        case _ => false
      }
    }

    override def hashCode(): Int = (x, y).##

    def canEqual(obj: Any): Boolean = obj.isInstanceOf[Point]
  }

  class ColoredPoint(override val x: Int, override val y: Int, val color: Colors.Value ) extends Point(x, y) {
    override def canEqual(obj: Any): Boolean = obj.isInstanceOf[ColoredPoint]

    override def equals(obj: Any): Boolean = {
      obj match {
        case that: ColoredPoint =>
          (that canEqual this) && this.color == that.color && super.equals(obj)
        case _ =>
          false
      }
    }

    override def hashCode(): Int = (x, y, color).##
  }

  object Colors extends Enumeration {
    val Red, Yellow, Green, Blue, Silver, Orange = Value
  }

}
