package week24

private class Solution15486 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        val workList = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until n) {
            val (t, p) = readln().split(" ").map { it.toInt() }
            workList.add(Pair(t, p))
        }

        val dp = MutableList(n + 1) { 0 }
        for (i in 0 until n){
            dp[i + 1] = maxOf(dp[i + 1], dp[i])

            val curWork = workList[i]
            if (i + curWork.first <= n) dp[i + curWork.first] = maxOf(dp[i + curWork.first], dp[i] + curWork.second)
        }

        println(dp[n])
    }
}

fun main() {
    Solution15486().solution()
}