package book.chapter13

object ImportPackages {
  /*
  Scala uses Java's global package space.
  _root_ is the most outer scala package, any other packages defined are sub packages of this package.
  Scala imports can be used anywhere and on abstract members.
  Scala lets us rename packages, classes or objects using import, for example:
    import java.sql.{Date => sqlDate}
  so both java.sql.Date and java.util.Date can co-exist.
  Exclusion can be applied by renaming targets to _ and the catch all import should come in last:
    Excluding blah, and imports everything else.
    import <packages>.{ blah => _, _}

  Scala implicitly imports
    java.lang._
    scala._
    Predef._
  to all files that ends with .scala

  Scope of Protection:
  Access modifiers in scala can be augmented with qualifiers.A modifier of the form private[X] or protected[X] means
  the access is private or protected up to X, where X is designates some closing package, class or objects.

  Companion objects and companion classes can access each other's private fields.

  Any definition defined in a package object are members of the package itself.
  Package object are frequently used to hold package-wise type alias and implicit conversions..
  Each of the package can have one package object and they are compiled to package.class,
  so a good practice is to have package object in package.scala.
  */
}
