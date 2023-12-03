package week33

/**
 * row의 형태가 다르면 절대로 같은 모양일 수 없기 때문에
 * row의 모양이 같은 경우를 센다
 */
private object Solution1034 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val rowMap = mutableMapOf<String, Int>()
        for (i in 0 until n) {
            val line = readln().trim()
            if (line in rowMap.keys) {
                rowMap[line] = rowMap[line]?.plus(1) ?: 1
            } else {
                rowMap[line] = 1
            }
        }

        val k = readln().toInt()

        var answer = 0
        for (key in rowMap.keys) {
            val zeroCnt = key.count { it == '0' }
            if (zeroCnt > k) continue
            if ((k - zeroCnt) % 2 != 0) continue
            answer = maxOf(answer, rowMap[key] ?: 0)
        }

        println(answer)
    }
}

fun main() {
    Solution1034.solution()
}