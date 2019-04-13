package book.chapter26

class Email private (val emailAddr: String)

// Extractor for Email class
object Email {
  val at = "@"
  // optional
  def apply(emailAddr: String) = new Email(emailAddr)
  def apply(name: String, domain: String): Email = new Email(name + at + domain)
  // if both unapply and unapplySeq are defined, only unapply is used
  def unapplySeq(email: Email): Option[(String, Seq[String])] = {
    val parts = email.emailAddr.split(at)
    if(parts.length == 2)
      Some(parts.head, parts(1).split("\\.").reverse)
    else None
  }
}

/*
Extractor vs case class

Case classes are shorter and more efficient due to their definitive nature as compiler can apply static type check and exhaustive pattern check.
However, their internal variable types are directly linked to the variable types in pattern match.

The advantage of extractors is view independence. Additional logics can be applied to the class members and different types can be returned.
 */
