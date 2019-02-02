package book.chapter19

// Unlike Java, scala does not allow raw types
trait Queue[+T] {
  def head: T
  def tail: Queue[T]
  // Queue would not be allowed to be covariant if lower bound wasn't used with enqueue type parameter
  def enqueue[U >: T](x: U): Queue[U]
}

/*
Covariant example:
With Queue[+T] and S being subtype of T, then Queue[S] is subtype of Queue[T].
A general rule of covariant is that the generic class should not contain any methods that take T as parameter.

Contravariant example:
with Queue[-T] and S being subtype of T, then Queue[S] is super type of Queue[T]
 */

// TODO: 19.4 Fast Track: How does scala perform variance annotation check?

object FunctionalQueue {
  /*
  Two lists are used to achieve constant access time.
  Implementation details of FunctionalQueue are hidden with private modifier and declared in its companion object.
   */
  private class FunctionalQueue[T](private[this] var leading: List[T], private[this] var trailing: List[T]) extends Queue[T] {
    private def mirror(): Unit = {
      if(leading.isEmpty) {
        while(trailing.nonEmpty) {
          leading = trailing.head :: leading
          trailing = trailing.tail
        }
      }
    }
    override def head: T = {
      mirror()
      leading.head
    }

    override def tail: Queue[T] = {
      mirror()
      new FunctionalQueue[T](leading.tail, trailing)
    }

    override def enqueue[U >: T](x: U): Queue[U] = new FunctionalQueue[U](leading, x :: trailing)
  }

  def apply[T](xs: T*): Queue[T] = new FunctionalQueue[T](xs.toList, Nil)
}
