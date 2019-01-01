package book.chapter10

object Spiral {
  val corner = Element("+")
  val space = Element(" ")
  def horizontalBar(width: Int) = Element('-', width, 1)
  def verticalBar(height: Int): Element = Element('|', 1, height)
  def getSpiral(edges: Int): Element = if(edges <= 0) corner else getSpiral(edges-1, corner, 0)
  private def getSpiral(edges: Int, spiral: Element, direction: Int): Element = {
    if(edges > 0) {
      val newSpiral = direction % 4 match {
        case 0 =>
          corner beside horizontalBar(spiral.width) above (spiral beside space)
        case 1 =>
          spiral above space beside (corner above verticalBar(spiral.height))
        case 2 =>
          space beside spiral above (horizontalBar(spiral.width) beside corner)
        case 3 =>
          verticalBar(spiral.height) above corner beside (space above spiral)
      }
      getSpiral(edges-1, newSpiral, direction+1)
    } else spiral
  }
  def main(args: Array[String]): Unit = {
    println(getSpiral(0))
    println(getSpiral(6))
    println(getSpiral(11))
    println(getSpiral(17))
  }
}
