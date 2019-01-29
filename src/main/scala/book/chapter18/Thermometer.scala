package book.chapter18

class Thermometer {
  var celsius: Double = _
  // fahrenheit is not defined as a variable but it can be derived from celsius
  def fahrenheit = celsius * 9 / 5 + 32
  def fahrenheit_= (f: Double) = celsius = (f - 32) * 5 / 9

  override def toString: String = s"Fahrenheit: ${fahrenheit}\tCelsius: $celsius"
}
