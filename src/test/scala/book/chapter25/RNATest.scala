package book.chapter25

import book.chapter25._
import org.scalatest.{FlatSpec, Matchers}

class RNATest extends FlatSpec with Matchers{
  "RNA Seq TAG" should "be the same when extracted out" in {
    val RNASeq = RNA(T, A, G)
    assert(RNASeq(0) == T)
    assert(RNASeq(1) == A)
    assert(RNASeq(2) == G)
  }

  "Seventeenth element in RNA seq" should "be the same when extracted out" in {
    val RNASeq = RNA(
      A, A, A, A, A,
      A, A, A, A, A,
      A, A, A, A, A,
      A, T
    )
    assert(RNASeq(16) == T)
  }

}
