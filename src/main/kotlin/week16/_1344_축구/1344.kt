package week16._1344_축구

fun main() = with(System.`in`.bufferedReader()) {
    val firstProb = readln().toInt() / 100.0
    val secondProb = readln().toInt() / 100.0

    val evenList = listOf(0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18)
    val dp = MutableList(19) { MutableList(19) { 0 } }
    dp[0][0] = 1
    for (row in 0 until 18) {
        for (col in 0 until 18) {
            if (dp[row][col] != 0) {
                dp[row + 1][col] += dp[row][col]
                dp[row + 1][col + 1] += dp[row][col]
            }
        }
    }
    var res1 = 0.0
    var res2 = 0.0
    var tmp: Double
    for (i in evenList){
        tmp = dp.last()[i].toDouble()
        for (cnt in 0 until i) tmp *= firstProb
        for (cnt in 0 until (18 - i)) tmp *= (1 - firstProb)
        res1 += tmp

        tmp = dp.last()[i].toDouble()
        for (cnt in 0 until i) tmp *= secondProb
        for (cnt in 0 until (18 - i)) tmp *= (1 - secondProb)
        res2 += tmp
    }

    println(1 - res1 * res2)
}