package book.chapter16

object ListExercise {
  def insertionSort(xs: List[Int]): List[Int] = {
    if(xs.isEmpty)  Nil
    else insert(xs.head, insertionSort(xs.tail))
  }

  private def insert(x: Int, xs: List[Int]): List[Int] = {
    if(xs.isEmpty || x <= xs.head) x :: xs
    else xs.head :: insert(x, xs.tail)
  }

  def append[T](xs: List[T], ys: List[T]): List[T] = {
    xs match {
      case Nil => ys
      case x :: xs1 => x :: append(xs1, ys)
    }
  }

  def mergeSort[T](less: (T, T) => Boolean)(list: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] = {
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if(less(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    }

    val n = list.length / 2
    if(n == 0) list
    else {
      val (left, right) = list.splitAt(n)
      merge(mergeSort(less)(left), mergeSort(less)(right))
    }
  }
}
