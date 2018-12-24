package book.chapter6

/*
Immutable objects trade offs:
Immutable objects are easier to reason about because their internal state doesn't change over time;
Immutable objects can be passed around freely while we may have to make a defensive copy of mutable object;
Immutable objects are better candidate for hash functions;
Immutable objects are thread safe after properly constructed.

However, Immutable objects could cause performance bottleneck if they are too large and needs to be altered which we will have to achieve by creating a new object.
 */
class Rational(val n: Int, val d: Int) {
  require(d != 0)
  println(toString)
  def unary_+ : Rational = this
  def unary_- : Rational = Rational(-n, d)

  /*
  It is best practice NOT to use _ or $ in naming convention:
  _ has many uses in sala and special identifiers are compiled to $<operator> in java.

  `` can be used to escape reserved scala keywords for example thread.`yield`().
   */
  def +(that: Rational): Rational = Rational(n*that.d + that.n*d, d * that.d)
  def -(that: Rational): Rational = Rational(n*that.d - that.n*d, d * that.d)
  def *(that: Rational): Rational = Rational(n*that.n, d*that.d)
  def /(that: Rational): Rational = Rational(n*that.d, d*that.n)
  def <(that: Rational): Boolean = n*that.d < that.n*d
  override def toString: String = s"$n/$d"

  override def equals(obj: scala.Any): Boolean = obj match {
    case that: Rational => n == that.n && d == that.d
    case _ => false
  }
}

object Rational extends App {
  private def greatestCommonDivisor(a: Int, b: Int): Int = {
    if(b == 0) {println(s"gcd: $a"); a} else greatestCommonDivisor(b, a % b)
  }
  def apply(numerator: Int, denominator: Int): Rational = {
    val gcd = greatestCommonDivisor(numerator.abs, denominator.abs)
    new Rational(numerator / gcd, denominator / gcd)
  }
  // A second constructor defined in the same class calling primary constructor is called auxiliary constructor.
  def apply(numerator: Int): Rational = Rational(numerator, 1)

  val half = new Rational(1, 2)
  assert(half.toString == "1/2")
  assert((-half).toString == "-1/2")

  val oneThird = new Rational(1, 3)
  assert(oneThird < half)

  val gcd = greatestCommonDivisor(7, 21)
  assert(gcd == 7)
  val gcd2 = greatestCommonDivisor(7, 22)
  assert(gcd2 == 1)

  val oneForth = Rational(4, 16)
  assert(oneForth.toString == "1/4")
  val oneSixth = half*oneThird
  assert(oneSixth.toString == "1/6")

  implicit def intToRational(x: Int): Rational = Rational(x)
  assert((1-half) == half)
  assert(half/2 == oneForth)
}
