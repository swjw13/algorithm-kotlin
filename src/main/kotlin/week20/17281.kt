package week20

private class Solution17281 {

    private var res = 0

    private val batterResults = mutableListOf<List<Int>>()

    private val visited = MutableList(9) { false }

    private val lineup = mutableListOf<Int>()

    private var inning = -1


    private fun game() {
        val entry =
            listOf(lineup[0], lineup[1], lineup[2], 0, lineup[3], lineup[4], lineup[5], lineup[6], lineup[7])
        var scoreTmp = 0
        var batterTmp = 0
        for (eachInning in batterResults) {
            var (b1, b2, b3) = listOf(0, 0, 0)
            var outs = 0
            while (outs < 3) {
                when (eachInning[entry[batterTmp]]) {
                    0 -> {
                        outs += 1
                    }

                    1 -> {
                        scoreTmp += b3
                        b3 = b2
                        b2 = b1
                        b1 = 1
                    }

                    2 -> {
                        scoreTmp += (b3 + b2)
                        b3 = b1
                        b2 = 1
                        b1 = 0
                    }

                    3 -> {
                        scoreTmp += (b3 + b2 + b1)
                        b3 = 1
                        b2 = 0
                        b1 = 0
                    }

                    4 -> {
                        scoreTmp += (b3 + b2 + b1 + 1)
                        b3 = 0
                        b2 = 0
                        b1 = 0
                    }
                }
                batterTmp = (batterTmp + 1) % 9
            }
        }
        res = maxOf(res, scoreTmp)
    }

    private fun permutation(turn: Int) {
        if (turn == 8) {
            game()
            return
        }

        for (i in 1 until 9) {
            if (!visited[i]) {
                visited[i] = true
                lineup.add(i)

                permutation(turn + 1)

                visited[i] = false
                lineup.removeLast()
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        inning = readln().toInt()
        for (i in 0 until inning) {
            batterResults.add(readln().split(" ").map { it.toInt() })
        }
        permutation(0)

        println(res)
    }
}

fun main() {
    Solution17281().solution()
}