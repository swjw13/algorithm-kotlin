package week10._10026_적록색약

// Array(N) {readln().toCharArray()}

fun main() = with(System.`in`.bufferedReader()) {

    val dxdy = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, 1), Pair(0, -1))

    val n = readln().toInt()

    val board = mutableListOf<MutableList<Char>>()
    val boardForRGError = mutableListOf<MutableList<Char>>()
    for (i in 0 until n) {
        val line = readln().trim().toList().toMutableList()

        board.add(line.copy())
        boardForRGError.add(line.copy())
    }

    fun bfs(board: MutableList<MutableList<Char>>, row: Int, col: Int, key: String) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(row, col))
        board[row][col] = ' '

        while (queue.isNotEmpty()) {
            val curPoint = queue.removeFirst()

            for (movement in dxdy) {
                val newRow = curPoint.first + movement.first
                val newCol = curPoint.second + movement.second

                if (newRow in 0 until n && newCol in 0 until n && board[newRow][newCol] in key) {
                    board[newRow][newCol] = ' '
                    queue.add(Pair(newRow, newCol))
                }
            }
        }
    }

    var ans1 = 0
    for (row in 0 until n) {
        for (col in 0 until n) {
            if (board[row][col] != ' ') {
                bfs(board, row, col, board[row][col].toString())
                ans1 += 1
            }
        }
    }

    var ans2 = 0
    for (row in 0 until n) {
        for (col in 0 until n) {
            if (boardForRGError[row][col].toString() in "RG") {
                bfs(boardForRGError, row, col, "RG")
                ans2 += 1
                continue
            }
            if (boardForRGError[row][col] in "B") {
                bfs(boardForRGError, row, col, "B")
                ans2 += 1
                continue
            }
        }
    }
    println("$ans1 $ans2")
}

fun <T> MutableList<T>.copy(): MutableList<T> {
    val newList = mutableListOf<T>()
    for (i in this) newList.add(i)
    return newList
}