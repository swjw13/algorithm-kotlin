package week25

/**
 * bfs로 tube를 탐색하면 해결이 될 수도...??
 */
private class Solution5214 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, _, m) = readln().split(" ").map { it.toInt() }

        val isIn = List(n + 1) { mutableListOf<Int>() }

        val tubes = mutableListOf<Set<Int>>()
        for (i in 0 until m) {
            val tube = readln().split(" ").map { it.toInt() }.toSet()
            for (j in tube) {
                isIn[j].add(i)
            }
            tubes.add(tube)
        }

        val visited = MutableList(n + 1) { false }
        visited[1] = true

        val queue = ArrayDeque<Pair<Int, Int>>().apply {
            add(Pair(1, 1))
        }

        while (queue.isNotEmpty()) {
            val (curPoint, curTurn) = queue.removeFirst()
            if (curPoint == n) {
                println(curTurn)
                return@with
            }

            val s = mutableSetOf<Int>()
            isIn[curPoint].forEach {
                for (point in tubes[it]) s.add(point)
            }

            for (point in s) {
                if (visited[point].not()) {
                    visited[point] = true
                    queue.add(Pair(point, curTurn + 1))
                }
            }
        }
        println(-1)
    }
}

fun main() {
    Solution5214().solution()
}