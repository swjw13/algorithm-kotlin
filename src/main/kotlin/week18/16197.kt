package week18

data class Point(
    val row: Int,
    val col: Int
)

data class QueueItem(
    val firstPoint: Point,
    val secondPoint: Point,
    val turn: Int
)

operator fun <T> List<T>.times(n: Int): List<T> {
    val res = mutableListOf<T>()
    repeat(n) {
        res.addAll(this)
    }
    return res.toList()
}

private class Solution16197 {
    companion object {
        private val dxdy = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        var firstPoint: Point? = null
        var secondPoint: Point? = null
        val visited = List(n + 3) { List(m + 3) { List(n + 3) { MutableList(m + 3) { false } } } }

        val board = mutableListOf<List<String>>().apply {
            add(listOf("*") * (m + 2))
        }

        for (i in 0 until n) {
            val first = listOf("*")
            val second = readln().trim().toList().map { it.toString() }
            board.add(first + second + first)

            if ("o" in second) {
                if (second.count { it == "o" } == 2) {
                    if (firstPoint == null) {
                        firstPoint = Point(i + 1, second.indexOf("o") + 1)
                        secondPoint = Point(i + 1, second.lastIndexOf("o") + 1)
                    } else secondPoint = Point(i + 1, second.indexOf("o") + 1)
                } else {
                    if (firstPoint == null) firstPoint = Point(i + 1, second.indexOf("o") + 1)
                    else secondPoint = Point(i + 1, second.indexOf("o") + 1)
                }
            }
        }
        board.add(listOf("*") * (m + 2))

        fun move(curRow: Int, curCol: Int, rowMove: Int, colMove: Int): Point? {
            return if (curRow + rowMove in 0 until n + 2 && curCol + colMove in 0 until m + 2) {
                if (board[curRow + rowMove][curCol + colMove] == "#") Point(curRow, curCol)
                else Point(curRow + rowMove, curCol + colMove)
            }
            else null
        }

        fun isOut(row: Int, col: Int): Boolean = (row in 1 until n + 1 && col in 1 until m + 1).not()

        if (firstPoint != null && secondPoint != null) {
            val queue = ArrayDeque<QueueItem>().apply {
                add(QueueItem(firstPoint, secondPoint, 0))
                visited[firstPoint.row][firstPoint.col][secondPoint.row][secondPoint.col] = true
            }

            while (queue.isNotEmpty()) {
                val curPair = queue.removeFirst()

                val firstRow = curPair.firstPoint.row
                val secondRow = curPair.secondPoint.row
                val firstCol = curPair.firstPoint.col
                val secondCol = curPair.secondPoint.col
                val curTurn = curPair.turn

                if (curTurn > 10) continue

                if (isOut(firstRow, firstCol) xor isOut(secondRow, secondCol)) {
                    println(curTurn)
                    return@with
                } else if (isOut(firstRow, firstCol).not() && isOut(secondRow, secondCol).not()) {
                    for (movement in dxdy) {
                        val newFirstPoint = move(firstRow, firstCol, movement.first, movement.second)
                        val newSecondPoint = move(secondRow, secondCol, movement.first, movement.second)

                        if (newFirstPoint != null && newSecondPoint != null) {
                            if (visited[newFirstPoint.row][newFirstPoint.col][newSecondPoint.row][newSecondPoint.col].not()) {
                                visited[newFirstPoint.row][newFirstPoint.col][newSecondPoint.row][newSecondPoint.col] = true
                                queue.add(QueueItem(newFirstPoint, newSecondPoint, curTurn + 1))
                            }
                        }
                    }
                }
            }
            println(-1)
        }
    }
}

fun main() {
    Solution16197().solution()
}