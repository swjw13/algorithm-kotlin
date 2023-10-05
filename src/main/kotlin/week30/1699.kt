package week30

private class Solution1699 {

    data class QueueElement(
        val point: Int,
        val turn: Int
    )

    fun solution() = with(System.`in`.bufferedReader()) {

        val dp = MutableList(100001) { Int.MAX_VALUE }
        val queue = ArrayDeque<QueueElement>().apply {
            var number = 1
            while (number * number <= 100000) {
                add(QueueElement(number * number, 1))
                dp[number * number] = 1
                number += 1
            }
        }

        while (queue.isNotEmpty()) {
            val (curPoint, curTurn) = queue.removeFirst()

            var number = 1
            while (curPoint + number * number <= 100000) {
                if (dp[curPoint + number * number] > curTurn + 1) {
                    queue.add(QueueElement(curPoint + number * number, curTurn + 1))
                    dp[curPoint + number * number] = curTurn + 1
                }
                number += 1
            }

        }

        val n = readln().trim().toInt()
        println(dp[n])
    }
}

fun main() {
    Solution1699().solution()
}