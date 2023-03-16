package week21

private class Solution1941 {
    val stack = mutableListOf<Int>()
    val visited = MutableList(25) { false }

    var cnt = 0
    val board = mutableListOf<String>()

    private fun isConnected(): Boolean {
        val queue = ArrayDeque<Int>().apply { add(stack.first()) }
        val connectionVisited = hashSetOf<Int>()
        connectionVisited.add(stack.first())

        while (queue.isNotEmpty()) {
            val curPoint = queue.removeFirst()
            var newPoint: Int

            when (curPoint % 5) {
                0 -> {
                    newPoint = curPoint + 1
                    if (newPoint in 0 until 25 && visited[newPoint] && (newPoint in connectionVisited).not()) {
                        connectionVisited.add(newPoint)
                        queue.add(newPoint)
                    }
                }

                4 -> {
                    newPoint = curPoint - 1
                    if (newPoint in 0 until 25 && visited[newPoint] && (newPoint in connectionVisited).not()) {
                        connectionVisited.add(newPoint)
                        queue.add(newPoint)
                    }
                }

                else -> {
                    newPoint = curPoint + 1
                    if (newPoint in 0 until 25 && visited[newPoint] && (newPoint in connectionVisited).not()) {
                        connectionVisited.add(newPoint)
                        queue.add(newPoint)
                    }
                    newPoint = curPoint - 1
                    if (newPoint in 0 until 25 && visited[newPoint] && (newPoint in connectionVisited).not()) {
                        connectionVisited.add(newPoint)
                        queue.add(newPoint)
                    }
                }
            }

            newPoint = curPoint - 5
            if (newPoint in 0 until 25 && visited[newPoint] && (newPoint in connectionVisited).not()) {
                connectionVisited.add(newPoint)
                queue.add(newPoint)
            }

            newPoint = curPoint + 5
            if (newPoint in 0 until 25 && visited[newPoint] && (newPoint in connectionVisited).not()) {
                connectionVisited.add(newPoint)
                queue.add(newPoint)
            }
        }
        return connectionVisited.size == 7
    }

    private fun sevenPrincess(): Boolean {
        if (!isConnected()) return false

        var sCnt = 0
        var yCnt = 0
        for (i in stack) {
            if (board[i / 5][i % 5] == 'Y') yCnt += 1
            else sCnt += 1
        }
        return sCnt > yCnt
    }

    fun combination(startPoint: Int) {
        if (stack.size == 7) {
            if (sevenPrincess()) {
                cnt += 1
            }
            return
        }
        for (i in startPoint until 25) {
            if (!visited[i]) {
                visited[i] = true
                stack.add(i)
                combination(i)
                stack.removeLast()
                visited[i] = false
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        repeat(5) {
            board.add(readln().trim())
        }

        combination(0)
        println(cnt)
    }
}

fun main() {
    Solution1941().solution()
}