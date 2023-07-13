package week28

private class Solution12869 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        when (n) {
            1 -> {
                val hp = readln().toInt()
                if (hp % 9 == 0) println(hp / 9) else println(hp / 9 + 1)
            }

            2 -> {
                val dp = List(60) { List(61) { MutableList(61) { false } } }
                val (hp1, hp2) = readln().split(" ").map { it.toInt() }
                dp[0][hp1][hp2] = true

                loop@ for (i in 0 until 60) {
                    for (one in 0..60) {
                        for (two in 0..60) {
                            if (dp[i][one][two]) {
                                val hp19 = maxOf(one - 9, 0)
                                val hp13 = maxOf(one - 3, 0)
                                val hp29 = maxOf(two - 9, 0)
                                val hp23 = maxOf(two - 3, 0)

                                if ((hp19 == 0 && hp23 == 0) || (hp13 == 0 && hp29 == 0)) {
                                    println(i + 1)
                                    break@loop
                                }

                                dp[i + 1][hp19][hp23] = true
                                dp[i + 1][hp13][hp29] = true
                            }
                        }
                    }
                }
            }

            3 -> {
                val dp = List(60) { List(61) { List(61) { MutableList(61) { false } } } }
                val (hp1, hp2, hp3) = readln().split(" ").map { it.toInt() }
                dp[0][hp1][hp2][hp3] = true

                loop@ for (i in 0 until 60) {
                    for (one in 0..60) {
                        for (two in 0..60) {
                            for (three in 0..60) {
                                if (dp[i][one][two][three]) {
                                    val hp11 = maxOf(0, one - 1)
                                    val hp13 = maxOf(0, one - 3)
                                    val hp19 = maxOf(0, one - 9)
                                    val hp21 = maxOf(0, two - 1)
                                    val hp23 = maxOf(0, two - 3)
                                    val hp29 = maxOf(0, two - 9)
                                    val hp31 = maxOf(0, three - 1)
                                    val hp33 = maxOf(0, three - 3)
                                    val hp39 = maxOf(0, three - 9)

                                    if ((hp11 == 0 && hp23 == 0 && hp39 == 0) ||
                                        (hp11 == 0 && hp29 == 0 && hp33 == 0) ||
                                        (hp13 == 0 && hp21 == 0 && hp39 == 0) ||
                                        (hp13 == 0 && hp29 == 0 && hp31 == 0) ||
                                        (hp19 == 0 && hp21 == 0 && hp33 == 0) ||
                                        (hp19 == 0 && hp23 == 0 && hp31 == 0)
                                    ) {
                                        println(i + 1)
                                        break@loop
                                    }
                                    dp[i + 1][hp11][hp23][hp39] = true
                                    dp[i + 1][hp11][hp29][hp33] = true
                                    dp[i + 1][hp13][hp21][hp39] = true
                                    dp[i + 1][hp13][hp29][hp31] = true
                                    dp[i + 1][hp19][hp21][hp33] = true
                                    dp[i + 1][hp19][hp23][hp31] = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun main() {
    Solution12869().solution()
}