package week10._2294_동전2

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val coins = mutableListOf<Int>()
    for (i in 0 until n) {
        coins.add(readln().toInt())
    }

    val dp = MutableList(k + 1) { Int.MAX_VALUE }

    coins.forEach {
        if (it < k + 1) dp[it] = 1
        for (i in 1..k - it) {
            if (dp[i] != Int.MAX_VALUE) {
                dp[i + it] = minOf(dp[i] + 1, dp[i + it])
            }
        }
    }

    println(if (dp[k] == Int.MAX_VALUE) -1 else dp[k])
}