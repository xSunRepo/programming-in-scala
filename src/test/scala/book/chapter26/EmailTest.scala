package book.chapter26

import org.scalatest.{FlatSpec, Matchers}

class EmailTest extends FlatSpec with Matchers {
  val emailList = Seq(
    "tom@gmail.com",
    "tom@yahoo.com",
    "alice@hotmail.com",
    "bob@binghamton.edu",
    "david@stonybrook.edu"
  )
  val emails = emailList map Email.apply
  "Pattern match with extractor" should "work as expected" in {
    val eduEmails = emails.filter {
      email =>
        email match {
          case Email(name, "edu", _*) => true
          case _ => false
        }
    }
    assert(eduEmails.length == 2)
  }
}
