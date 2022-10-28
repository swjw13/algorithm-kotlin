package week5._16926_배열돌리기

fun main() {

    fun rotate(lst: List<MutableList<Int>>, startRow: Int, startCol: Int, rowSize: Int, colSize: Int, rotateSize: Int) {
        val deque = ArrayDeque<Int>()

        readln()

        for (i in startCol until startCol + colSize){
            deque.add(lst[startRow][i])
        }
        for (i in startRow + 1 until startRow + rowSize) {
            deque.add(lst[i][startCol + colSize - 1])
        }
        for (i in startCol + colSize - 2 downTo startCol) {
            deque.add(lst[startRow + rowSize - 1][i])
        }
        for (i in startRow + rowSize - 2 downTo startRow + 1) {
            deque.add(lst[i][startCol])
        }

        for (i in 0 until rotateSize){
            val firstElement = deque.removeFirst()
            deque.add(firstElement)
        }

        for (i in startCol until startCol + colSize){
            lst[startRow][i] = deque.removeFirst()
        }
        for (i in startRow + 1 until startRow + rowSize) {
            lst[i][startCol + colSize - 1] = deque.removeFirst()
        }
        for (i in startCol + colSize - 2 downTo startCol) {
            lst[startRow + rowSize - 1][i] = deque.removeFirst()
        }
        for (i in startRow + rowSize - 2 downTo startRow + 1) {
            lst[i][startRow] = deque.removeFirst()
        }
    }

    with(System.`in`.bufferedReader()) {
        val(n, m, r) = readLine().split(" ").map { it.toInt() }
        val board = mutableListOf<MutableList<Int>>()
        for (i in 0 until n){
            board.add(readLine().split(" ").map { it.toInt() }.toMutableList())
        }

        var row = 0
        var col = 0
        while ((row < n / 2) && (col < m / 2)){
            rotate(board, row, col, n - 2 * row, m - 2 * row, r)
            row += 1
            col += 1
        }

        board.forEach {
            println(it.joinToString(" "))
        }
    }
}