package book.chapter25

import scala.collection.generic.CanBuildFrom
import scala.collection.mutable
import scala.collection.immutable

final class PrefixMap[T] extends mutable.Map[String, T] with mutable.MapLike[String, T, PrefixMap[T]] {
  /*
    Nodes in PrefixMap are expected to have small amount of elements except the first few levels,
    hence the use of immutable map as it's more compact and efficient
   */
  private var suffixes: immutable.Map[Char, PrefixMap[T]] = Map.empty
  private var value: Option[T] = None

  override def get(key: String): Option[T] = if(key.isEmpty) value else suffixes.get(key.head).flatMap(_.get(key.substring(1)))

  def withPrefix(prefix: String): PrefixMap[T] = {
    if(prefix.isEmpty)  this
    else {
      val lead = prefix.head
      suffixes.get(lead) match {
        case None =>
          suffixes = suffixes + (lead -> PrefixMap.empty)
        case _ =>
      }
      suffixes(lead).withPrefix(prefix.substring(1))
    }
  }

  override def update(key: String, value: T): Unit = {
    withPrefix(key).value = Some(value)
  }

  override def remove(key: String): Option[T] = {
    if(key.isEmpty) {
      val temp = value
      value = None
      temp
    } else
      suffixes.get(key.head).flatMap(_.remove(key.substring(1)))
  }

  override def +=(kv: (String, T)): PrefixMap.this.type = {
    update(kv._1, kv._2)
    this
  }

  override def -=(key: String): PrefixMap.this.type = {
    remove(key); this
  }

  override def iterator: Iterator[(String, T)] = {
    (for(v <- value.iterator) yield ("" -> v)) ++
      (for(
        (char, m) <- suffixes.iterator;
        (s, v) <- m.iterator
        ) yield (char +: s, v))
  }

  override def empty = PrefixMap.empty
}

object PrefixMap {
  def empty[T] = new PrefixMap[T]

  def apply[T](kvs: (String, T)*): PrefixMap[T] = {
    val newMap = empty[T]
    for((k, v) <- kvs) {
      newMap.update(k, v)
    }
    newMap
  }

  def newBuilder[T]: mutable.Builder[(String, T), PrefixMap[T]] = new mutable.MapBuilder[String, T, PrefixMap[T]](empty[T])

  implicit def canBuildFrom[T]: CanBuildFrom[PrefixMap[_], (String, T), PrefixMap[T]] = new CanBuildFrom[PrefixMap[_], (String, T), PrefixMap[T]] {
    override def apply(from: PrefixMap[_]): mutable.Builder[(String, T), PrefixMap[T]] = newBuilder[T]

    override def apply(): mutable.Builder[(String, T), PrefixMap[T]] = newBuilder[T]
  }
}
