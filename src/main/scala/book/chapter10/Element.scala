package book.chapter10

/*
Vals are computed during object construction whereas defs are evaluated upon invocation.
Scala lets programmers use parenthesis optionally for method that takes no parameters to fill in the semantic gap (array.length) between java.
However, as a best practice, parenthesis are treated as an indication for side effects.

Scala only has two namespace: values and types.
Parameterless fields or methods can be overridden interchangeably.

Method invocation are dynamically bounded, meaning the method on the actual object is invoked when called.
 */
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if(contents.isEmpty) 0 else contents(0).length
  def above(that: Element): Element = Element(widen(that.width).contents ++ that.widen(width).contents)
  def beside(that: Element): Element = {
    Element(
      for{
        (line1, line2) <- heighten(that.height).contents zip that.heighten(height).contents
      } yield line1 + line2
    )
  }
  protected def widen(w: Int): Element = {
    if(w <= width) this
    else {
      val left = Element(' ', (w - width)/2, height)
      val right = Element(' ', w - width - left.width, height)
      left beside this beside right
    }
  }
  protected def heighten(h: Int): Element = {
    if(h <= height) this
    else {
      val top = Element(' ', width, (h - height)/2)
      val bottom = Element(' ', width, h - height - top.height)
      top above this above bottom
    }
  }

  override def toString: String = contents.mkString("\n")
}

object Element {
  def apply(array: Array[String]): Element = new ArrayElement(array)
  def apply(line: String): Element = new LineElement(line)
  def apply(char: Char, width: Int, height: Int): Element = new UniformElement(char, width, height)
  private class ArrayElement(val contents: Array[String]) extends Element
  private class LineElement(line: String) extends Element {
    override val contents = Array(line)
  }
  private class UniformElement(char: Char, width: Int, height: Int) extends Element {
    override def contents: Array[String] = {
      val line = char.toString * width
      Array.fill(height)(line)
    }
  }
}
