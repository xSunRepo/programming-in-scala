import scala.io.Source

object FileCharsCounter extends App {
  if (args.length > 0) {
    val lines = Source.fromFile(args(0)).getLines().toSeq
    val longestLine = lines.reduceLeft {
      (a, b) => if (a.length > b.length) a else b
    }
    val maxCharCount = longestLine.length.toString.length
    for (line <- lines) {
      val padding = " " * (maxCharCount - line.length.toString.length)
      println(s"$padding${line.length} | $line")
    }
  } else
    Console.err.println("Full file path missing.")
}
