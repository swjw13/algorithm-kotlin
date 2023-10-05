package week32

/**
 * 투포인터 알고리즘
 * 위치를 기준으로 정렬한 뒤
 * 조건에 맞는지 안맞는지 판단하여 포인터 조정
 */

private class Solution12892 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, d) = readln().split(" ").map { it.toInt() }
        val lst = mutableListOf<List<Long>>()
        for (i in 0 until n) {
            lst.add(readln().split(" ").map { it.toLong() })
        }

        lst.sortBy { it[0] }

        var result = 0L
        var highIdx = 0
        var lowIdx = 0
        var high: Long = lst[0][0]
        var low: Long = lst[0][0]
        var total: Long = lst[0][1]

        while (highIdx < n) {
            if (high - low < d) {
                result = maxOf(result, total)
                highIdx += 1
                if (highIdx < n) {
                    high = lst[highIdx][0]
                    total += lst[highIdx][1]
                }
            } else {
                total -= lst[lowIdx][1]
                lowIdx += 1
                low = lst[lowIdx][0]
            }
        }

        println(result)
    }
}

fun main() {
    Solution12892().solution()
}