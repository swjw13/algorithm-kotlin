package week11._2302_극장좌석

fun main() = with(System.`in`.bufferedReader()) {
    var res = 1
    val dp = mutableListOf(0, 1, 2)
    for (i in 3..41) {
        dp.add(dp[i - 2] + dp[i - 1])
    }

    val n = readln().toInt()
    val m = readln().toInt()

    val vipNumbers = mutableListOf<Int>()
    for (i in 0 until m) {
        vipNumbers.add(readln().toInt())
    }

    var cnt = 0
    for (i in 1..n) {
        if (i in vipNumbers) {
            if (cnt != 0) {
                res *= dp[cnt]
            }
            cnt = 0
            continue
        }
        cnt += 1
    }

    if (cnt != 0) res *= dp[cnt]

    println(res)
}