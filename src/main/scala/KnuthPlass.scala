class KnuthPlass(text: String, width: Int) {
  private val words: Array[String] = this.text.split(" ")
  private val offsets: Array[Int] = this.words.scanLeft(0)(_ + _.length)

  private var minima: Array[Double] = Array(0.0) ++ Array.fill(this.words.length)(Math.pow(10, 20))
  private var breaks: Array[Int] = Array.fill(this.words.length + 1)(0)

  private def getWords(): Array[String] = words
  private def getOffsets(): Array[Int] = offsets
  private def getMinima(): Array[Double] = minima
  private def getBreaks(): Array[Int] = breaks

  private def setMinima(minima: Array[Double]): Unit = this.minima = minima
  private def setBreaks(breaks: Array[Int]): Unit = this.breaks = breaks

  private def cost(i: Int, j: Int): Double = {
    val w = this.getOffsets()(j) - this.getOffsets()(i) + j - i - 1
    if (w > this.width) {
      Math.pow(10, 10) * (w - this.width)
    } else {
      this.getMinima()(i) + Math.pow(this.width - w, 2)
    }
  }

  // Based upon the SMAWK algorithm to find the minimum value of each row (of words)
  // on an implicitly defined totally monotone matrix
  // paper can be seen here https://mathscinet.ams.org/mathscinet-getitem?mr=0895444
  // an improvement TODO make this abide to the functional paradigm
  private def smawk(rows: List[Int], columns: List[Int]): Unit = {
    var stack: List[Int] = List()

    //search through rows
    var i: Int = 0
    var j: Int = 0
    while (i < rows.length) {
      if (stack.length > 0) {
        val column: Int = columns(stack.length - 1)
        val costOfStackOnColumn: Double = cost(stack.last, column)
        val costOfColumnOnRow: Double = cost(rows(i), column)

        if (costOfStackOnColumn < costOfColumnOnRow) {
          if (stack.length < columns.length) {
            stack = stack.appended(rows(i))
          }
          i = i + 1
        } else {
          stack = stack.dropRight(1)
        }
      } else {
        stack = stack.appended(rows(i))
        i = i + 1
      }
    }

    if (columns.length > 1) {
      val slicedColumns = columns
        .grouped(2)
        .filter(_.length > 1)
        .map { case List(a, b) => b }.toList

      smawk(stack, slicedColumns)
    }

    // search through columns diagonally of rows
    i = 0
    var end: Int = 0
    while (j < columns.length) {
      if (j + 1 < columns.length) {
        end = this.getBreaks()(columns(j + 1))
      } else {
        end = stack.last
      }
      val costOfElementFromMatrix = cost(stack(i), columns(j))
      if (costOfElementFromMatrix < this.getMinima()(columns(j))) {
        var minima = this.getMinima()
        var breaks = this.getBreaks()

        minima(columns(j)) = costOfElementFromMatrix
        breaks(columns(j)) = stack(i)

        this.setMinima(minima)
        this.setBreaks(breaks)
      }
      if (stack(i) < end) {
        i = i + 1
      } else {
        j = j + 2
      }
    }
  }

  private def matrix_offset_minima(word_length: Int): Unit = {
    var offset: Int = 0
    var n: Int = word_length
    var i: Int = 0

    while (true) {
      val r = Math.min(n, Math.pow(2, i + 1)).toInt
      val edge = Math.pow(2, i).toInt + offset
      val rows  = Range(start = 0 + offset, end = edge).toList
      val columns = Range(start = edge, end = r + offset).toList

      smawk(rows, columns)

      val x = this.getMinima()(r - 1 + offset)

      var break = false
      for (j <- Range(Math.pow(2, i).toInt, r - 1)) {
        if (!break) {
          val y = cost(j + offset, r - 1 + offset)
          if (y <= x) {
            n = n - j
            i = 0
            offset = offset + j
            break = true
          }
        }
      }
      if (!break) {
        i = i + 1
        if (r == n) {
          return
        }
      }
    }

  }

  private def toLines(count: Int): List[String] = {
    if (count == 0) {
      return List()
    }
    val i = this.getBreaks()(count)
    toLines(i) :+ this.getWords().slice(i, count).mkString(" ")
  }

  def apply(): String = {
    matrix_offset_minima(this.getWords().length + 1)
    val lines: List[String] = toLines(this.getWords().length)
    lines.mkString("\n")
  }
}
