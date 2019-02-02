package book.chapter18

/*
Scala implicitly define getter and setter for vars where the vars are made private and only accessible in the class itself.
 */
class Time {
  private[this] var h = 12
  private[this] var m = 0

  // define getters and setters explicitly
  def hour = h
  def hour_= (x: Int) = {
    require(0 <= x && x <= 24)
    h = x
  }

  def minute = m
  def minute_= (x: Int) = {
    require( 0 <= x && x <= 60)
    m = x
  }
}
