package book.chapter18

import book.chapter18.Time
import org.scalatest.{FlatSpec, Matchers}

class TimeTest extends FlatSpec with Matchers {
  val time = new Time
  "Time" should "throw exception when encountered invalid hour and minute" in {
    assertThrows[IllegalArgumentException](
      time.hour_=(25)
    )
    assertThrows[IllegalArgumentException](
      time.minute_=(61)
    )
    assert(time.hour == 12)
  }
}
