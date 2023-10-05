package week28

private class Solution19538 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        val connected = mutableListOf(listOf<Int>())
        for (i in 0 until n) {
            val lst = readln().split(" ").map { it.toInt() }
            connected.add(lst)
        }

        val visited = MutableList(n + 1) { -1 }
        val queue = ArrayDeque<Int>()
        var curTurn = 0

        val m = readln().toInt()
        readln().split(" ").map { it.toInt() }.forEach {
            visited[it] = curTurn
            queue.add(it)
        }

        while (true) {
            val visitingQueue = mutableSetOf<Int>()
            val tempList = mutableListOf<Int>()
            while (queue.isNotEmpty()) {
                val curPoint = queue.removeFirst()
                for (c in connected[curPoint]) {
                    if (c != 0 && visited[c] == -1) visitingQueue.add(c)
                }
            }

            if (visitingQueue.isEmpty()) break

            for (v in visitingQueue) {
                var cnt = 0
                for (c in connected[v]) {
                    if (c == 0) break
                    if (visited[c] != -1) cnt += 1
                }

                if (cnt >= connected[v].size / 2) {
                    tempList.add(v)
                }
            }

            for (v in tempList) {
                visited[v] = curTurn + 1
                queue.add(v)
            }

            curTurn += 1
        }

        println(visited.subList(1, n + 1).joinToString(" "))
    }
}

fun main() {
    Solution19538().solution()
}