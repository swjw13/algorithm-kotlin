package week30

/**
 * 겪었던 어려움
 * - Long 범위를 벗어나는 값은 어떻게 처리하지?
 *
 * 첫 접근: dfs
 * -> 그러나 시간초과로 실패
 *
 * 다음 접근: dp
 *
 */
private class Solution1082 {

    private lateinit var prices: List<Int>
    private var answer = ""

    fun String.compare(other: String): String {
        val newThis = this.replaceFirst(Regex("0*"), "")
        val newOther = other.replaceFirst(Regex("0*"), "")
        if (newThis == newOther) return newThis
        if (newThis.length > newOther.length) return newThis
        if (newThis.length < newOther.length) return newOther

        var idx = 0
        while (newThis[idx] == newOther[idx]) idx += 1
        return if (newThis[idx] > newOther[idx]) newThis else newOther
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        prices = readln().split(" ").map { it.toInt() }
        val m = readln().toInt()

        val priceToString = mutableMapOf<Int, Int>()
        for (i in prices.indices) {
            if (prices[i] !in priceToString.keys) {
                priceToString[prices[i]] = i
            } else {
                priceToString[prices[i]] = maxOf(priceToString[prices[i]]!!, i)
            }
        }

        val dp = MutableList(m + 1) { "" }
        for (price in priceToString.keys) {
            if (price <= m) {
                dp[price] = "${priceToString[price]}"
            }
        }

        for (prevPrice in 1 until m) {
            if (dp[prevPrice].isNotEmpty()) {
                for (newPrice in priceToString.keys) {
                    if (prevPrice + newPrice <= m) {
                        if (dp[newPrice + prevPrice].isEmpty()) {
                            dp[newPrice + prevPrice] = "${dp[prevPrice]}${priceToString[newPrice]}"
                        } else {
                            dp[newPrice + prevPrice] =
                                dp[newPrice + prevPrice].compare("${dp[prevPrice]}${priceToString[newPrice]}")
                        }
                    }
                }
            }
        }

        for (price in m downTo 1) {
            answer = answer.compare(dp[price])
        }

        if (answer.isEmpty()) println("0") else println(answer)
    }
}

fun main() {
    Solution1082().solution()
}