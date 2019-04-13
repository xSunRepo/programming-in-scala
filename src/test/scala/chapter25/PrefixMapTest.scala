package chapter25

import book.chapter25.PrefixMap
import org.scalatest.{FlatSpec, Matchers}

class PrefixMapTest extends FlatSpec with Matchers {
  val encryptionMap = PrefixMap(
    ("What" -> 1),
    ("is" -> 2),
    ("the" -> 3),
    ("password" -> 4),
    ("to" -> 5),
    ("pass" -> 6),
    ("all" -> 7),
    ("unit" -> 8),
    ("tests" -> 0),
    ("!" -> 10)
  )

  "PrefixMap" must "return correct mapping value" in {
    assert(encryptionMap.get("dragon") == None)
    assert(encryptionMap.get("to") == Some(5))
  }

  it must "return updated values correctly" in {
    val key = "tests"
    val value = 9
    assert(encryptionMap.get(key) == Some(0))
    encryptionMap.update(key, value)
    assert(encryptionMap.get(key) == Some(value))
  }

  it must "support remove operation" in {
    val key = "!"
    val value = 10
    assert(encryptionMap.get(key) == Some(value))
    assert(encryptionMap.remove(key) == Some(value))
    assert(encryptionMap.get(key) == None)
  }
}
