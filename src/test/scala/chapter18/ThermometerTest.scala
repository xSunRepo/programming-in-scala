package chapter18

import book.chapter18.Thermometer
import org.scalatest.{FlatSpec, Matchers}

class ThermometerTest extends FlatSpec with Matchers {
  val thermometer = new Thermometer
  "Thermometer" should "have celsius initialized to 0" in {
    assert(thermometer.celsius == 0d)
  }

  it should "have fahrenheit initialized to 32" in {
    assert(thermometer.fahrenheit == 32d)
  }

  it should "have celsius and fahrenheit equal to each other at -40" in {
    thermometer.fahrenheit_=(-40)
    assert(thermometer.celsius == thermometer.fahrenheit)
  }
}
