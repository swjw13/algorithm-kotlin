package week29

import java.util.*


private class Solution1633 {
    fun solution(){
        val lst = mutableListOf<List<Int>>()
        var cnt = 0

        val scan = Scanner(System.`in`)

        while (scan.hasNextInt()) {
            val a = scan.nextInt()
            val b = scan.nextInt()

            lst.add(listOf(a, b))
            cnt += 1
        }

        val dp = List(cnt + 1) { List(16) { MutableList(16) { -1 } } }
        dp[0][15][15] = 0
        dp[0][14][15] = lst[0][0]
        dp[0][15][14] = lst[0][1]

        for (i in 0 until cnt - 1){
            for (j in 15 downTo 0){
                for (k in 15 downTo 0){
                    if (dp[i][j][k] >= 0){
                        dp[i + 1][j][k] = maxOf(dp[i + 1][j][k], dp[i][j][k])

                        if (j - 1 >= 0){
                            dp[i + 1][j - 1][k] = maxOf(dp[i + 1][j - 1][k], dp[i][j][k] + lst[i + 1][0])
                        }
                        if (k - 1 >= 0){
                            dp[i + 1][j][k - 1] = maxOf(dp[i + 1][j][k - 1], dp[i][j][k] + lst[i + 1][1])
                        }
                    }
                }
            }
        }

        println(dp[cnt - 1][0][0])

    }
}

fun main(){
    Solution1633().solution()
}
