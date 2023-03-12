package week20

private class Solution16928 {

    private data class QueueElement(
        val point: Int,
        val turn: Int
    )

    fun solution() = with(System.`in`.bufferedReader()) {
        val movingMap = mutableMapOf<Int, Int>().apply {
            for (i in 1..100) set(i, i)
        }
        val visited = MutableList(101) { false }

        val (n, m) = readln().split(" ").map { it.toInt() }
        for (idx in 0 until n) {
            val (start, end) = readln().split(" ").map { it.toInt() }
            movingMap[start] = end
        }

        for (idx in 0 until m) {
            val (start, end) = readln().split(" ").map { it.toInt() }
            movingMap[start] = end
        }

        val queue = ArrayDeque<QueueElement>().apply {
            add(QueueElement(1, 0))
        }
        while (queue.isNotEmpty()) {
            val curPoint = queue.removeFirst()
            if (curPoint.point == 100) {
                println(curPoint.turn)
                return@with
            }

            for (i in 1..6) {
                val newPoint = movingMap[curPoint.point + i]

                newPoint?.let {
                    if (visited[it].not()) {
                        visited[it] = true
                        queue.add(QueueElement(it, curPoint.turn + 1))
                    }
                }

            }
        }
    }
}

fun main() {
    Solution16928().solution()
}