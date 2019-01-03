package book.chapter11

object Hierarchy {
  /*
  Scala hierarchy diagram: resource/scalaclasshierarchy.png
  AnyVal is suppose to only contain one val and it uses java primitives directly. AnyVal implementations are suppose to be final.
  AnyRef is equivalent to java.lang.Object.
  Null is subclass of implementations of AnyRef.
  Nothing is subclass of Any class, and it's normally used to abnormal termination.

  == and equals are value comparison whereas eq is reference comparison.
  AnyVal subclasses use hashCode in equality comparison, so same values of different types can be compared properly (1, 1L, 1F, 1D);
  equals and == cannot be overridden for AnyVal subtypes
  Scala added extra flavors to the AnyVal subtypes with an additional class Rich<Type>. They are implicitly converted when methods in Rich<Type> are invoked.
   */
  def main(args: Array[String]): Unit = {
    assert(1 == 1L)
    assert(new Integer(1) == new java.lang.Long(1))
  }
}
