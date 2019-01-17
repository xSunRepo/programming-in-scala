package book.chapter15

// sealed keyword indicates all the case classes that extends from the marked abstract class or trait are defined within the same file.
sealed abstract class Expr
/*
Benefit of case keyword:
  -adds a factory method to instantiate objects
  -all constructor parameters are default to vals
  -default implementation of toString, hashCode and equals
  -a copy method to alter fields
 */
case class Var(x: String) extends Expr
case class Number(x: Double) extends Expr
case class UniOp(op: String, arg: Expr) extends Expr
case class BinOp(op: String, left: Expr, right: Expr) extends Expr

object Expr {
  def simplifyExpr(expr: Expr): Expr = {
    expr match {
        // constructor match and variable match
      case UniOp("-", UniOp("-", e)) => simplifyExpr(e)
      case BinOp("+", e, Number(0)) => simplifyExpr(e)
      case BinOp("*", e, Number(1)) => simplifyExpr(e)
      case UniOp(op, e) => UniOp(op, simplifyExpr(e))
      case BinOp(op, left, right) => BinOp(op, simplifyExpr(left), simplifyExpr(right))
        // wildcard match
      case _ => expr
    }
  }
}
