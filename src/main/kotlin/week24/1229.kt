package week24

private class Solution1229 {

    val sixNumber = mutableListOf(1, 6)

    private fun addSixNumber(maxValue: Int) {
        var idx = 3
        var tmp = 6
        while (true) {
            val numberToAdd = tmp + idx + 3 * (idx - 1)
            if (numberToAdd <= maxValue) {
                sixNumber.add(numberToAdd)
                idx += 1
                tmp = numberToAdd
            } else return
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        addSixNumber(n)

        val dp = MutableList(n + 1) { it }

        for (i in sixNumber) {
            if (i <= n) dp[i] = 1
            else break
        }

        if (n > 146858) {
            for (i in 1 until 146858) {
                for (j in sixNumber) {
                    if (i + j <= 146858) {
                        dp[i + j] = minOf(dp[i + j], dp[i] + 1)
                    } else break
                }
            }

            for (i in 0 until sixNumber.size) {
                for (j in i until sixNumber.size) {
                    if (sixNumber[i] + sixNumber[j] <= n) dp[sixNumber[i] + sixNumber[j]] = 2
                    else break
                }
            }

            if (dp[n] == n) println(3)
            else println(dp[n])

        } else {

            for (i in 1 until n) {
                for (j in sixNumber) {
                    if (i + j <= n) {
                        dp[i + j] = minOf(dp[i + j], dp[i] + 1)
                    } else break
                }
            }
            println(dp[n])
        }
    }
}

fun main() {
    Solution1229().solution()
}