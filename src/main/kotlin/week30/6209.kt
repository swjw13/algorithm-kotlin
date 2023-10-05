package week30

private class Solution6209 {

    fun List<Int>.binSearch(value: Int): Int {
        if (value > this.last()) return this.size

        var low = 0
        var high = this.size - 1
        while (low <= high) {
            val mid = (low + high) / 2
            if (value < this[mid]) {
                high = mid - 1
            } else if (value == this[mid]) {
                return mid
            } else {
                low = mid + 1
            }
        }
        return low // 최소로 높은 값을 찾기 위함
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (d, n, m) = readln().split(" ").map { it.toInt() }
        if (n == 0) {
            println(d)
            return@with
        }

        val tmp = mutableListOf<Int>()

        for (i in 0 until n) {
            tmp.add(readln().toInt())
        }
        tmp.sort()

        var low = 0
        var high = 1000000000
        while (low <= high) {
            val mid = (low + high) / 2

            var cnt = 0
            var curPoint = 0
            while (true) {
                val nextIdx = tmp.binSearch(curPoint + mid)
                if (nextIdx == n) {
                    break
                } else {
                    cnt += 1
                    curPoint = tmp[nextIdx]
                }
            }

            if (cnt < n - m) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }

        println(high)

    }
}

fun main() {
    Solution6209().solution()
}