package week32

/**
 * 부르트포스로 해결
 * 50 * 50 * 50 이라 충분히 가능하다 판단
 * 모든 쌍의 건물들을 체크해서 그 사이의 건물들을 판단하는 방법으로 해결
 * 함수의 방정식 이용
 *
 */
private class Solution1027 {

    fun getMathParameter(x1: Int, y1: Int, x2: Int, y2: Int): List<Double> {
        if (y1 == y2) return listOf(0.0, y1.toDouble())
        val a: Double = (y2 - y1).toDouble() / (x2 - x1).toDouble()
        val b: Double = y1 - a * x1

        return listOf(a, b)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        val lst = readln().split(" ").map { it.toInt() }

        val result = List(n) { MutableList(n) { false } }
        for (cityOne in 0 until n) {
            for (cityTwo in cityOne + 1 until n) {
                var isWatchable = true
                val (a, b) = getMathParameter(cityOne, lst[cityOne], cityTwo, lst[cityTwo])
                for (cityMiddle in cityOne + 1 until cityTwo) {
                    val y = a * cityMiddle + b
                    if (lst[cityMiddle] >= y) {
                        isWatchable = false
                        break
                    }
                }

                if (isWatchable) {
                    result[cityOne][cityTwo] = true
                    result[cityTwo][cityOne] = true
                }
            }
        }

        println(result.maxOf { oneCity -> oneCity.count { it } })
    }
}

fun main() {
    Solution1027().solution()
}