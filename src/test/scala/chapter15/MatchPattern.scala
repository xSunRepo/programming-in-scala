package chapter15

import book.chapter15._
import org.scalatest.{FlatSpec, Matchers}

class MatchPattern extends FlatSpec with Matchers {
  /*
    Like any other scala expression, match pattern results in a value.
    A MatchError will be thrown if the argument happen to be uncovered.
  */
  /*
    Literals, vals and singleton objects can be used in constant pattern.
  */
  def describe(param: Any): String = {
    param match {
      case 5 => "Five"
      case true => "True"
      case "hello" => "Hello"
      case Nil => "Not initialised list"
        // wildcard pattern acts as default, it covers all the unspecified cases.
      case _ => "Wildcard!"
    }
  }
  "Integer 5" should "be described as Five" in {
    assert(describe(5) == "Five")
  }
  "Boolean true" should "be described as True" in {
    assert(describe(true) == "True")
  }
  "String hello" should "remain as hello" in {
    assert(describe("hello") == "Hello")
  }
  "An empty list" should "is a Not initialised list" in {
    assert(describe(List())=="Not initialised list")
  }
  "If not matched" should "then print out WildCard!" in {
    assert(describe("huh") == "Wildcard!")
  }

  // variable pattern also catches all like wildcard pattern, but on top of that, the variable used refers to the object being matched.
  "e" should "not match pi" in {
    import scala.math.{E, Pi}
    assert(E match {
      // upper case escapes variable match
      case Pi => false
      case _ => true
    })
    import scala.math.{Pi => pi}
    assert(E match {
      // back tick escapes lower case variable name
      case `pi` => false
      case _ => true
    })
  }

  // constructor pattern
  "Constructed object" should "match its constructor applied with same parameters" in {
    val doubleExpr = BinOp("*", Var("x"), Number(2))
    val result = doubleExpr match {
      // This actually feels like a constant pattern because the constructor just creates the object.
      case BinOp("*", Var("x"), Number(2)) => "Deep match!"
      case _ => "Whatever"
    }
  }

  "List" should "be matched with list pattern" in {
    val list = List(0, 0, 1, 0, 0, 0)
    val listMatch = list match {
      case List(_, _, 1) => false
      case List(_, _, 1, _, _, _) => true
      case _ => false
    }
    assert(listMatch)
    val listMatchWithUnknownLength = list match {
      case List(_, _, 1, _*) => true
      case _ => false
    }
    assert(listMatchWithUnknownLength)
  }

  /*
    Typed pattern is normally used instead of type check (isInstanceOf) and type cast (asInstanceOf).
    Scala is statically typed, meaning all the type information are erased at runtime (Type Erasure).
    The only exception is Array where the type information is stored as value.
  */
  "Typed Pattern" should "pass with examples" in {
    def generalSize(arg: Any): Int = {
      arg match {
        case str: String => str.length
        case map: Map[_, _] => map.size
        case _ => -1
      }
    }
    assert(generalSize("hello") == 5)
    assert(generalSize(Map(1->1, 2->2)) == 2)
  }

  // Variable binding
  "Variable binding used in pattern match" should "let us refer to an object through a variable with partial or full conditions" in {
    def simplifyAbsExpr(expr: Expr): Expr = {
      expr match {
        case UniOp("abs", e @ UniOp("abs", _)) => e
        case _ => throw new IllegalStateException("Boom")
      }
    }
    assertThrows[IllegalStateException](
      simplifyAbsExpr(Number(0))
    )

    val baseAbsExpr = UniOp("abs", Number(0))
    val simplifiedAbsExpr = simplifyAbsExpr(UniOp("abs", baseAbsExpr))
    assert(baseAbsExpr == simplifiedAbsExpr)
  }

  "Pattern guard" should "apply condition using if after specifying cases" in {
    def simplifyAdd(expr: Expr): Expr = {
      expr match {
        case BinOp("+", x, y) if x==y => BinOp("*", x, Number(2))
        case _ => expr
      }
    }
    assert(simplifyAdd(BinOp("+", Var("x"), Var("x"))) == BinOp("*", Var("x"), Number(2)))
  }

  "A pattern can be used instead of using an identifier" should "yield same results" in {
    val (int, string) = Tuple2(123, "hello")
    assert(int == 123)
    assert(string == "hello")
  }

  "Case sequence is a function literal, so it" can "be used anywhere a function literal is required" in {
    def defaultValue: Option[Int] => Int = {
      case Some(x) => x
      case None => 0
    }
    assert(defaultValue(Some(5))==5)
    assert(defaultValue(None) == 0)
  }

  "A sequence of cases is a partial function and it" should "throw MatchError when applied on an object without said partial function defined" in {
    val second: PartialFunction[List[Int], Int] = {
      case _ :: y :: _ => y
    }
    val list = List(1, 2, 3)
    assert(!second.isDefinedAt(Nil))
    assert(second.isDefinedAt(list))
    assert(second(List(1, 2, 3)) == 2)
    assertThrows[MatchError](second(Nil))
  }
}
