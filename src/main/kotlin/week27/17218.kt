package week27

/**
 *     A B C
 *   A
 *   B   A
 *   D     AB AB
 *         AB AB
 */

private class Solution17218 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val firstWord = readln().trim()
        val secondWord = readln().trim()

        val firstLength = firstWord.length
        val secondLength = secondWord.length

        val dp = List(firstLength + 1) { MutableList(secondLength + 1) { "" } }

        for (first in 0 until firstLength) {
            for (second in 0 until secondLength) {
                if (firstWord[first] == secondWord[second]) {
                    if (dp[first + 1][second + 1].length <= dp[first][second].length + 1) {
                        dp[first + 1][second + 1] = dp[first][second] + firstWord[first]
                    }
                } else {
                    if (dp[first + 1][second + 1].length <= dp[first][second].length) {
                        dp[first + 1][second + 1] = dp[first][second]
                    }
                    if (dp[first][second + 1].length <= dp[first][second].length) {
                        dp[first][second + 1] = dp[first][second]
                    }
                    if (dp[first + 1][second].length <= dp[first][second].length) {
                        dp[first + 1][second] = dp[first][second]
                    }
                }
            }
        }

        var answer = ""
        for (i in 0..firstLength) {
            if (dp[i][secondLength].length > answer.length) answer = dp[i][secondLength]
        }
        for (i in 0..secondLength) {
            if (dp[firstLength][i].length > answer.length) answer = dp[firstLength][i]
        }
        println(answer)
    }
}

fun main() {
    Solution17218().solution()
}
