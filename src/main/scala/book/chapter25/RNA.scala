package book.chapter25

import scala.collection.generic.CanBuildFrom
import scala.collection.mutable.ArrayBuffer
import scala.collection.{IndexedSeqLike, mutable}

abstract class Base
// Adenine, Thymine, Guanine, Uracil
object A extends Base
object T extends Base
object G extends Base
object U extends Base

object Base {
  // This works because collections implement Function1
  def toInt: Base => Int = Map(A -> 0, T -> 1, G -> 2, U -> 3)
  def fromInt: Int => Base = Array(A, T, G, U)
}

// extends IndexedSeqLike[Base, RNA], so inherited methods can return the correct type.
final class RNA private (groups: Array[Int], val length: Int) extends IndexedSeq[Base] with IndexedSeqLike[Base, RNA] {
  import RNA._

  override protected[this] def newBuilder: mutable.Builder[Base, RNA] = RNA.newBuilder

  override def apply(idx: Int): Base = {
    if(idx < 0 || length <= idx)
      throw new ArrayIndexOutOfBoundsException(s"Indexing to $idx with length being $length")
    else
      Base.fromInt((groups(idx / N) >> (idx % N * 2)) & M)
  }

  // Optional optimization
  override def foreach[U](f: Base => U): Unit = {
    var i = 0
    var b = 0
    while(i < length) {
      b = if(i % N == 0) groups(i) else b >>> S
      f(Base.fromInt(b & M))
      i+=1
    }
  }
}

object RNA {
  // packet size - total of 4 combinations
  final val S = 2
  // number of Bases that can fit into a word
  final val N = 32 / S
  // Mask bits
  final val M = (1 << S) - 1

  /*
  This apply method is the only way to construct a RNA since it has a private constructor.
  This design abstracts away the implementation details of the RNA class, which means it can be changes in the future
  without affecting client code.
  */
  def fromSeq(bufs: Seq[Base]): RNA = {
    val groups = new Array[Int]((bufs.length + N - 1) / N)
    for(i <- 0 until bufs.length)
      groups(i / N) += Base.toInt(bufs(i)) << (i % N) * 2
    new RNA(groups, bufs.length)
  }

  def apply(bases: Base*): RNA = fromSeq(bases)

  def newBuilder: mutable.Builder[Base, RNA] = new ArrayBuffer[Base] mapResult fromSeq

  implicit val cbf: CanBuildFrom[RNA, Base, RNA] = new CanBuildFrom[RNA, Base, RNA] {
    override def apply(): mutable.Builder[Base, RNA] = newBuilder
    override def apply(from: RNA): mutable.Builder[Base, RNA] = newBuilder
  }
}
