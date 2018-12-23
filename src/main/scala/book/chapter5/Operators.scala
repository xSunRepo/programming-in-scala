package book.chapter5

object Operators extends App{
  /*
  The only four identifiers can be used as prefix operator: '+', '-', '!', '~'.
  These four operators are transformed by scala compiler to unary_<operator> during compilation time.
  */
  val neg = -1
  val negneg = -neg
  val -- = neg.unary_-

  // Infix operator
  val negSum = neg + neg

  // Suffix operator: method that takes no parameters
  // Common practice is to leave off the trailing () if the method called has no side effects.
  val str = (neg + 9) toString

  /*
  Left shift and right unsigned shift leaves shifted bits with 0,
  whereas right shift leaves shifted bits with the value of the most significant bit.
  */
  val rightShiftedNeg = neg >> 31
  val leftShiftedNeg = neg << 2
  val rightUnsignedShiftedNeg = neg >>> 31

  val one = 1
  val two = 2
  val three = one | two
  val zero = one & two
  val negTwo = ~one
  val wrong = !true

  def pepper = {
    println("pepper")
    true
  }

  def salt = {
    println("salt")
    false
  }

  /*
  Infix operators || and && are short circuited, see below examples.

   */
  if(pepper || salt) println("Only pepper is printed.")
  if(!(salt && pepper)) println("Only salt is printed.")
  if(pepper | salt) println("Both pepper and salt are printed")
  if(!(salt & pepper)) println("Both pepper and salt are printed")

  /*
  Scala operator order precedence:
  (all other special characters)
  * / %
  + -
  :
  = !
  < >
  &
  ^
  |
  (all letters)
  (all assignment operators)

  Scala keeps track of method calling order with the above table.
  Ex. 2 + 2 * 7 is transformed to 2 + ( 2 * 7) by scala compiler.
  This can be quite hacky because *doSomething will have higher precedence than +doSomething.

  Operators ends with ':' are evaluated on the right operand: a :: b == b.::(a);
  moreover, this impacts the evaluation order as well: a :: b :: c == a :: (b :: c).

  It's a good practice to use parenthesis for clarification.
  */
}
