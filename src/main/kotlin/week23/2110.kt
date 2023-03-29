package week23

private class Solution2110 {

    private fun binarySearch(lst: List<Int>, element: Int): Int {
        var start = 0
        var end = lst.size - 1
        var idx = -1
        while (start <= end) {
            val mid = (start + end) / 2
            when {
                lst[mid] < element -> {
                    start = mid + 1
                }

                lst[mid] == element -> {
                    return mid
                }

                else -> {
                    idx = mid
                    end = mid - 1
                }
            }
        }
        return idx
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        var res = 0

        val (n, c) = readln().split(" ").map { it.toInt() }
        val point = mutableListOf<Int>()
        repeat(n) {
            val curPoint = readln().toInt()
            point.add(curPoint)
        }
        point.sort()

        var low = 1
        var high = point.maxOf { it }

        while (low <= high) {
            // 공유기 사이 거리의 최솟값
            val mid = (low + high) / 2

            var curPoint = point.first()
            var cnt = 1
            var idx = 0
            while (cnt < c && idx != -1) {
                idx = binarySearch(point, curPoint + mid)
                if (idx != -1) {
                    cnt += 1
                    curPoint = point[idx]
                }
            }

            if (cnt == c) {
                res = mid
                low = mid + 1
            } else {
                high = mid - 1
            }
        }

        println(res)
    }
}

fun main() {
    Solution2110().solution()
}