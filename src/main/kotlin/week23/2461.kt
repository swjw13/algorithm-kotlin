package week23

import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

class Solution2461 {

    data class HeapElement(
        val value: Int,
        val row: Int,
        val col: Int
    )

    fun solution() = with(System.`in`.bufferedReader()) {
        var res = Int.MAX_VALUE
        var maxValue = 0

        val (n, m) = readln().split(" ").map { it.toInt() }
        val board = mutableListOf<List<Int>>()
        repeat(n) {
            val line = readln().split(" ").map { it.toInt() }
            board.add(line.sorted())
        }
        val heap = PriorityQueue<HeapElement> { e1, e2 -> e1.value.compareTo(e2.value) }.apply {
            for (i in 0 until n) {
                add(HeapElement(board[i][0], i, 0))
                maxValue = max(maxValue, board[i][0])
            }
        }

        while (heap.isNotEmpty()) {
            val curElement = heap.poll()
            res = min(res, maxValue - curElement.value)
            if (curElement.col == m - 1) break

            val nextElement = board[curElement.row][curElement.col + 1]
            maxValue = max(maxValue, nextElement)
            heap.add(HeapElement(nextElement, curElement.row, curElement.col + 1))
        }

        println(res)
    }
}

fun main() {
    Solution2461().solution()
}