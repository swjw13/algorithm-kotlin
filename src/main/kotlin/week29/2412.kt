package week29

private class Solution2412 {

    data class QueueElement(
        val point: Pair<Int, Int>,
        val turn: Int
    )

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, T) = readln().split(" ").map { it.toInt() }
        val holes = hashSetOf<Pair<Int, Int>>()

        for (i in 0 until n) {
            val (x, y) = readln().split(" ").map { it.toInt() }
            holes.add(Pair(x, y))
        }

        val visited = hashSetOf<Pair<Int, Int>>()

        val queue = ArrayDeque<QueueElement>().apply {
            add(QueueElement(Pair(0, 0), 0))
        }

        while (queue.isNotEmpty()) {
            val (curPoint, curTurn) = queue.removeFirst()

            if (curPoint.second >= T) {
                println(curTurn)
                return@with
            }

            for (row in -2..2) {
                for (col in -2..2) {
                    val newPoint = Pair(curPoint.first + row, curPoint.second + col)
                    if (newPoint in holes) {
                        if (newPoint !in visited) {
                            visited.add(newPoint)
                            queue.add(QueueElement(newPoint, curTurn + 1))
                        }
                    }
                }
            }
        }
        println(-1)
    }
}

fun main() {
    Solution2412().solution()
}