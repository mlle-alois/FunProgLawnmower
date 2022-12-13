package test

object ex2 {
  def trianglePascal(n: Int): Unit = {
    for(i <- 0 to n) {
      for(j <- 0 to i) {
        print(pascal(j, i) + " ")
      }
      println()
    }
  }

  def pascal(colonne: Int, ligne: Int): Int = {
    if (colonne == 0 || colonne == ligne) 1
    else pascal(colonne - 1, ligne - 1) + pascal(colonne, ligne - 1)
  }

  def main(args: Array[String]): Unit = {
    trianglePascal(5)
    trianglePascal(15)
  }
}
