package week5._4485_젤다

import java.util.PriorityQueue

/**
 * comparable 함수에 compareTo 사용하기
 */

fun main() {

    val dxy = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

    fun bfs(lst: List<List<Int>>, n: Int): Int {
        val heap: PriorityQueue<Triple<Int, Int, Int>> = PriorityQueue { p1, p2 ->
            p1.first.compareTo(p2.first)
        }

        val dist = List(n) { MutableList(n) { Int.MAX_VALUE } }
        dist[0][0] = lst[0][0]
        heap.add(Triple(dist[0][0], 0, 0))

        while (heap.isNotEmpty()) {
            val curPoint = heap.poll()
            if (curPoint.second == n - 1 && curPoint.third == n - 1) return curPoint.first

            if (dist[curPoint.second][curPoint.third] < curPoint.first) continue

            for (movement in dxy) {
                val newRow = curPoint.second + movement.first
                val newCol = curPoint.third + movement.second

                if ((newRow in 0 until n && newCol in 0 until n).not()) continue

                val newDist = curPoint.first + lst[newRow][newCol]

                if (newDist < dist[newRow][newCol]) {
                    dist[newRow][newCol] = newDist
                    heap.add(Triple(newDist, newRow, newCol))
                }
            }
        }
        throw java.lang.Exception("잘못된 입력입니다.")
    }

    with(System.`in`.bufferedReader()) {
        var turn = 1
        while (true) {
            val n = readLine().trim().toInt()
            if (n == 0) return

            val lst = mutableListOf<List<Int>>()
            for(i in 0 until n){
                lst.add(readLine().trim().split(" ").map { it.toInt() })
            }
            println("Problem ${turn}: ${bfs(lst, n)}")
            turn += 1
        }
    }
}