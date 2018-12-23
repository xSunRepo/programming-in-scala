package book.chapter5

object Types extends App{
  /*
  Byte	8-bit signed two’s complement integer (-2^7 to 2^7 - 1, inclusive)
  Short	16-bit signed two’s complement integer (-2^15 to 2^15 - 1, inclusive)
  Int		32-bit signed two’s complement integer (-2^31 to 2^31 - 1, inclusive)
  Long	64-bit signed two’s complement integer (-2^63 to 2^63 - 1, inclusive)
  Char	16-bit unsigned Unicode character (0 to 2^16 - 1, inclusive)
  String	a sequence of Chars
  Float	32-bit IEEE 754 single-precision float
  Double	64-bit IEEE 754 double-precision float
  Boolean	true or false

  Byte, Short, Int, Long, and Char are known as integral types;
  Integral types, float, double are known as numeric types.
   */

  val byte: Byte = 127
  // val byte: Byte = 128 will return an error as 128 exceeds the range of Byte.
  val short: Short = 128

  // Int is the default type for numeric literals.
  val int = 1

  // Scala compiler represents numbers in base 10.
  val hex = 0xcafebabe

  val lowerLong = 123l
  val upperLong = 123L

  val lowerFloat = 150E3f
  val upperFloat = 150E3F

  val double = 150E5
  val lowerDouble = 150d
  val upperDouble = 150D
  val decimalDouble = 1.0

  // Unicode
  val B\u0041\u0044 = "This is B\u0041\u0044!"

  /*
  Scala symbols are interned String;
  They are good for comparison and act as an identifier.
   */
  val symbol = 'asymbol

  // String interpolation
  val whoAmI = "a programmer"
  val s = s"I am $whoAmI, and I'm obsessed with ${Math.PI}!"
  val raw = raw"No escape character here: \ '"
  val f = f"Pi to the fifth decimal: ${Math.PI}%.5f, refer to java.util.Formatter."

  val sss =
    """
      Badly aligned raw triple ",
        Doesn't look very nice.
    """

  val alignedSss =
    """
      | This is aligned,
      | and it looks nice.
    """.stripMargin
}
