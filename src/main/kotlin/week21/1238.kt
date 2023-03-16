package week21

import java.util.PriorityQueue

private class Solution1238 {

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, x) = readln().split(" ").map { it.toInt() }
        val connectedGraph = List(n + 1) { mutableListOf<Pair<Int, Int>>() }

        for (i in 0 until m) {
            val (start, end, weight) = readln().split(" ").map { it.toInt() }
            connectedGraph[start].add(Pair(end, weight))
        }

        fun dijkstra(point: Int): MutableList<Int> {
            val dist = MutableList(n + 1) { Int.MAX_VALUE }
            val heap = PriorityQueue<Pair<Int, Int>> { p1, p2 -> p1.second.compareTo(p2.second) }.apply {
                add(Pair(point, 0))
                dist[point] = 0
            }

            while (heap.isNotEmpty()) {
                val (curPoint, curDist) = heap.poll()

                if (dist[curPoint] < curDist) continue

                for (connected in connectedGraph[curPoint]) {
                    val newDist = curDist + connected.second

                    if (dist[connected.first] > newDist) {
                        dist[connected.first] = newDist
                        heap.add(Pair(connected.first, newDist))
                    }
                }
            }

            return dist
        }

        /**
         * start from x
         */
        val distanceFromX = dijkstra(x)

        /**
         * end to x
         */
        var res = 0
        for (i in 1..n) {
            val toX = dijkstra(i)[x]
            res = maxOf(res, toX + distanceFromX[i])

        }

        println(res)
    }
}

fun main() {
    Solution1238().solution()
}