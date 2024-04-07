package week41

private object Solution2565 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        val lines = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until n) {
            val (a, b) = readLine().split(" ").map { it.toInt() }
            lines.add(Pair(a, b))
        }

        lines.sortBy { it.first }

        val dp = MutableList(n) { 1 }
        for (i in 0 until n) {
            for (j in 0 until i){
                if (lines[j].second < lines[i].second){
                    dp[i] = maxOf(dp[i], dp[j] + 1)
                }
            }
        }

        println(n - dp.maxOf { it })
    }
}

fun main() {
    Solution2565.solution()
}