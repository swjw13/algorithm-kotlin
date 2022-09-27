import java.lang.Integer.min

class Solution {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var answer = Int.MAX_VALUE
        val totalSize = queue1.size + queue2.size
        var totalValue = 0L
        val totalQueue1 = mutableListOf<Int>()
        val totalQueue2 = mutableListOf<Int>()

        for (i in queue1) {
            totalValue += i.toLong()
            totalQueue1.add(i)
        }
        for (i in queue2) {
            totalValue += i.toLong()
            totalQueue1.add(i)
            totalQueue2.add(i)
        }
        for (i in queue1) {
            totalQueue2.add(i)
        }

        if (totalValue % 2 == 1L) return -1

        fun findAnswer(s: Int, e: Int): Int {
            if (s == 0) {
                if (e == queue1.size - 1) {
                    return 0
                } else if (e < queue1.size) {
                    return (e + 1) + (s + queue1.size)
                } else {
                    return (e + 1 - queue1.size) + s
                }
            } else if (s >= queue1.size) {
                if (e == totalSize - 1) {
                    return (s - queue1.size)
                } else {
                    return (e + 1 - queue1.size) + s
                }
            } else {
                if (e < queue1.size - 1) {
                    return (e + 1) + (s + queue1.size)
                } else if (e == queue1.size - 1) {
                    return s
                } else if (e == totalSize - 1) {
                    return (queue1.size) + s
                } else {
                    return (e + 1 - queue1.size) + s
                }
            }
        }

        var startIdx = 0
        var endIdx = 0
        var total: Long = totalQueue1[0].toLong()

        while (endIdx in startIdx until totalSize) {
            if (total == totalValue / 2L) {
                answer = min(answer, findAnswer(startIdx, endIdx))
                total -= totalQueue1[startIdx].toLong()
                startIdx += 1
            } else if (total < totalValue / 2L) {
                endIdx += 1
                if (endIdx < totalSize) total += totalQueue1[endIdx].toLong()
            } else {
                total -= totalQueue1[startIdx].toLong()
                startIdx += 1
            }
        }

        startIdx = 0
        endIdx = 0
        total = totalQueue2[0].toLong()

        while (endIdx in startIdx until totalSize) {
            if (total == totalValue / 2L) {
                answer = min(answer, findAnswer(startIdx, endIdx))
                total -= totalQueue2[startIdx].toLong()
                startIdx += 1
            } else if (total < totalValue / 2L) {
                endIdx += 1
                if (endIdx < totalSize) total += totalQueue2[endIdx].toLong()
            } else {
                total -= totalQueue2[startIdx].toLong()
                startIdx += 1
            }
        }
        return if (answer == Int.MAX_VALUE) -1
        else answer
    }
}