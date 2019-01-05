package book.chapter12

import book.chapter6.Rational

import scala.collection.mutable.ArrayBuffer

/*
Scala encourage programmers lean toward rich interface when facing the thin/rich interface trade off problem
because traits support default method implementation.

The trait Ordered[T] is an example of rich interface, comparison operators such as <, <=, >, >= are implemented by default
and the programmer is only asked to implement compare.
Note Ordered[T] doesn't include equals due to type erasure at runtime.

*/
class RichRational(override val n: Int, override val d: Int) extends Rational(n, d) with Ordered[Rational] {
  override def compare(that: Rational): Int = n * that.d - that.n * d

  override def <(that: Rational): Boolean = super.<(that)
}

abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}

class BasicIntQueue extends IntQueue {
  private val buffer = new ArrayBuffer[Int]
  override def put(x: Int): Unit = {
    println(s"When am I invoked? $x")
    buffer += x
  }
  override def get(): Int = buffer.remove(0)
}

// Stackable traits
trait IncreaseByOne extends IntQueue {
  abstract override def put(x: Int): Unit = {
    val y = x+1
    println(s"IncreaseByOne is invoked, $x is now $y")
    super.put(y)
    println("Reached the end of IncreaseByOne")
  }
}

trait NegativeNumberFilter extends IntQueue {
  println("Expression in traits seem to be evaluated by the constructor of implemented class, this shall print NegativeNumberFilter")
  abstract override def put(x: Int): Unit = {
    println(s"NegativeNumberFiler is invoked!")
    if(x >= 0) super.put(x) else println(s"$x is filtered out")
    println("Reached the end of NegativeNumberFilter")
  }
}

trait Double extends IntQueue {
  abstract override def put(x: Int): Unit ={
    val y = x * 2
    println(s"Double is invoked, $x is now $y")
    super.put(y)
    println("Reached the end of Double")
  }
}

/*
To Trait or Not to Trait?
  -Will it be reused by multiple classes or traits? Only traits can be freely mixed into the hierarchy.
  -Is Java Compatibility a concern? Abstract Class should be used, but java actually also supports default implementation in interface now.
  -Will it be distributed in compiled form? Any change in traits recompile implemented classes, so abstract class is preferred.
*/
object Traits {
  def main(args: Array[String]): Unit = {
    assert(new RichRational(1, 2) > new RichRational(1, 3))

    val intQueue = new BasicIntQueue
    intQueue.put(10)
    intQueue.put(20)
    assert(intQueue.get() == 10)
    assert(intQueue.get() == 20)

    /*
    Mix in stack modifications
    The order of mixing in stack modification traits is crucial, it normally gets invoked from the right most trait to the left.

    Linearization:
    BasicIntQueue -> NegativeNumberFilter -> IncreaseByOne -> Double -> IntQueue

    Revisit 12.6 and 12.7 when in doubt.
    */
    val filterIncreaseThenDouble = new BasicIntQueue with Double with IncreaseByOne with NegativeNumberFilter
    filterIncreaseThenDouble.put(-1)
    filterIncreaseThenDouble.put(1)
    filterIncreaseThenDouble.put(20)
    assert(filterIncreaseThenDouble.get() == 4)
    assert(filterIncreaseThenDouble.get() == 42)

    val increaseFilterThenDouble = new BasicIntQueue with Double with NegativeNumberFilter with IncreaseByOne
    increaseFilterThenDouble.put(-1)
    increaseFilterThenDouble.put(1)
    increaseFilterThenDouble.put(20)
    assert(increaseFilterThenDouble.get() == 0)
    assert(increaseFilterThenDouble.get() == 4)
    assert(increaseFilterThenDouble.get() == 42)

    // Hint: dynamically bounded method invocation
    val whatWillHappen: IncreaseByOne = filterIncreaseThenDouble
    whatWillHappen.put(-1)
    whatWillHappen.put(5)
    println(whatWillHappen.get())
  }
}