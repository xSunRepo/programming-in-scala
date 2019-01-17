package book.chapter15

import book.chapter10.Element

object ExprFormatter {
  private val opGroup = Array(
    Set("|", "||"),
    Set("&", "&&"),
    Set("^"),
    Set("==", "!="),
    Set("<", "<=", ">", ">="),
    Set("+", "-"),
    Set("*", "%")
  )
  private val precedence = {
    val assocs = for {
      i <- 0 until opGroup.length
      op <- opGroup(i)
    } yield op -> i
    assocs.toMap
  }

  private val unaryPrecedence = opGroup.length
  private val fractionPrecedence  = -1

  private def format(expr: Expr, prec: Int): Element = {
    expr match {
      case Var(str) => Element(str)
      case Number(x) =>
        val xStr = x.toString
        Element(if(xStr.endsWith(".0")) xStr.substring(0, xStr.length-2) else xStr)
      case UniOp(op, e) =>
        Element(op) beside format(e, unaryPrecedence)
      case BinOp("/", left, right) =>
        val top = format(left, fractionPrecedence)
        val bot = format(right, fractionPrecedence)
        val line = Element('-', top.width max bot.width, 1)
        val frac = top above line above bot
        if(prec != fractionPrecedence)  frac
        else  Element(" ") beside frac beside Element(" ")
      case BinOp(op, left, right) =>
        val opPrec = precedence(op)
        val l = format(left, opPrec)
        val r = format(right, opPrec+1)
        val f = l beside Element(" " + op + " ") beside r
        if(prec <= opPrec) f
        else  Element("(") beside f beside Element(")")
    }
  }

  def format(expr: Expr): Element = format(expr, 0)
}

object Expression extends App {
  // 1/2 * (x+1)
  val e1 = BinOp("*", BinOp("/", Number(1), Number(2)), BinOp("+", Var("x"), Number(1)))
  // x/2 + 1.5/x
  val e2 = BinOp("+", BinOp("/", Var("x"), Number(2)), BinOp("/", Number(1.5), Var("x")))
  val e3 = BinOp("/", e1, e2)
  for(e <- Array(e1, e2, e3)) {
    val elem = ExprFormatter.format(e)
    println(elem.toString + "\n")
  }
}
