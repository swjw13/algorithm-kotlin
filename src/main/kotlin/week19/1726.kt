package week19

private class Solution1726 {

    private val dxdy = listOf(Pair(-1, -1), Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))

    private data class Point(
        val row: Int,
        val col: Int,
        val dir: Int
    )

    private data class QueueElement(
        val point: Point,
        val turn: Int
    )

    fun rotate(curDir: Int, isLeft: Boolean): Int = when (curDir) {
        1 -> if (isLeft) 4 else 3
        2 -> if (isLeft) 3 else 4
        3 -> if (isLeft) 1 else 2
        4 -> if (isLeft) 2 else 1
        else -> throw NoSuchElementException()
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val board = mutableListOf<List<Int>>()
        for (i in 0 until n) {
            val line = readln().split(" ").map { it.toInt() }
            board.add(line)
        }

        val startPoint = readln().split(" ").map { it.toInt() }.run {
            Point(get(0) - 1, get(1) - 1, get(2))
        }
        val endPoint = readln().split(" ").map { it.toInt() }.run {
            Point(get(0) - 1, get(1) - 1, get(2))
        }

        val queue = ArrayDeque<QueueElement>().apply {
            add(QueueElement(startPoint, 0))
        }
        val visited = List(4) { List(n) { MutableList(m) { false } } }

        while (queue.isNotEmpty()) {
            val curElement = queue.removeFirst()

            if (curElement.point == endPoint) {
                println(curElement.turn)
                return@with
            }

            val movement = dxdy[curElement.point.dir]
            for (i in 1..3) {
                if (curElement.point.row + movement.first * i !in 0 until n ||
                    curElement.point.col + movement.second * i !in 0 until m) break
                else if (board[curElement.point.row + movement.first * i][curElement.point.col + movement.second * i] == 1) break
                else if (visited[curElement.point.dir - 1][curElement.point.row + movement.first * i][curElement.point.col + movement.second * i].not()) {
                    visited[curElement.point.dir - 1][curElement.point.row + movement.first * i][curElement.point.col + movement.second * i] =
                        true
                    queue.add(
                        QueueElement(
                            Point(
                                curElement.point.row + movement.first * i,
                                curElement.point.col + movement.second * i,
                                curElement.point.dir
                            ),
                            curElement.turn + 1
                        )
                    )
                }
            }
            if (visited[rotate(curElement.point.dir, false) - 1][curElement.point.row][curElement.point.col].not()) {
                visited[rotate(curElement.point.dir, false) - 1][curElement.point.row][curElement.point.col] = true
                queue.add(
                    QueueElement(
                        Point(
                            curElement.point.row,
                            curElement.point.col,
                            rotate(curElement.point.dir, false)
                        ), curElement.turn + 1
                    )
                )
            }

            if (visited[rotate(curElement.point.dir, true) - 1][curElement.point.row][curElement.point.col].not()) {
                visited[rotate(curElement.point.dir, true) - 1][curElement.point.row][curElement.point.col] = true
                queue.add(
                    QueueElement(
                        Point(
                            curElement.point.row,
                            curElement.point.col,
                            rotate(curElement.point.dir, true)
                        ), curElement.turn + 1
                    )
                )
            }
        }
    }
}

fun main() {
    Solution1726().solution()
}