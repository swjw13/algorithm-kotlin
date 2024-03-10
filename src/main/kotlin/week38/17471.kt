package week38

import kotlin.math.abs

private object Solution17471 {

    private lateinit var visited: MutableList<Boolean>
    private lateinit var peopleCount: List<Int>
    private val stack = mutableListOf<Int>()
    private val connected: MutableMap<Int, List<Int>> = mutableMapOf()
    private var result = Int.MAX_VALUE

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        visited = MutableList(n + 1) { false }
        peopleCount = readln().split(" ").map(String::toInt)

        for (i in 1..n) {
            val lst = readln().split(" ").map { it.toInt() }
            connected[i] = lst.subList(1, lst.size)
        }

        for (i in 1 until n) {
            combination(0, i, n, 1)
        }

        if (result == Int.MAX_VALUE) println(-1) else println(result)
    }

    fun combination(depth: Int, maxDepth: Int, n: Int, point: Int) {
        if (depth == maxDepth) {
            val startOfA = stack.first()
            var startOfB: Int = -1
            for (i in 1..n) {
                if (!visited[i]) {
                    startOfB = i
                    break
                }
            }
            val smallVisited = MutableList(n + 1) { false }.apply {
                set(0, true)
            }
            val queueOfA = ArrayDeque<Int>().apply {
                smallVisited[startOfA] = true
                add(startOfA)
            }

            while (queueOfA.isNotEmpty()) {
                val curPoint = queueOfA.removeFirst()
                for (connectedPoint in connected[curPoint]!!) {
                    if (visited[connectedPoint] && !smallVisited[connectedPoint]) {
                        smallVisited[connectedPoint] = true
                        queueOfA.add(connectedPoint)
                    }
                }
            }

            val queueOfB = ArrayDeque<Int>().apply {
                smallVisited[startOfB] = true
                add(startOfB)
            }

            while (queueOfB.isNotEmpty()) {
                val curPoint = queueOfB.removeFirst()
                for (connectedPoint in connected[curPoint]!!) {
                    if (!visited[connectedPoint] && !smallVisited[connectedPoint]) {
                        smallVisited[connectedPoint] = true
                        queueOfB.add(connectedPoint)
                    }
                }
            }

            if (smallVisited.all { it }) {
                val total = peopleCount.sum()
                val groupA = peopleCount.withIndex().filter {
                    visited[it.index + 1]
                }.sumOf { it.value }

                result = minOf(result, abs(groupA - (total - groupA)))
            }

        } else {
            for (i in point..n) {
                visited[i] = true
                stack.add(i)
                combination(depth + 1, maxDepth, n, i + 1)
                stack.removeLast()
                visited[i] = false
            }
        }
    }
}

fun main() {
    Solution17471.solution()
}