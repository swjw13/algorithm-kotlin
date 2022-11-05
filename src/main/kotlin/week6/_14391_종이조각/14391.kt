package week6._14391_종이조각

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val board = mutableListOf<String>()
    for (i in 0 until n) {
        val input = readLine()
        board.add(input)
    }

    val bits = mutableListOf("0", "1")
    for (i in 1 until m) {
        val newRes = mutableListOf<String>()
        bits.forEach {
            newRes.add("1$it")
            newRes.add("0$it")
        }
        bits.clear()
        bits.addAll(newRes)
    }

    fun scoring(mask: List<String>): Int {
        val scoreBoard = List(n) { MutableList(m) { "" } }
        val maxScores = mutableMapOf<Pair<Int, Int>, Int>()

        for (row in 0 until n) {
            for (col in 0 until m) {
                when (mask[row][col]) {
                    '0' -> { // 가로 형태일 경우
                        if (col != 0 && mask[row][col - 1] == '0') {
                            scoreBoard[row][col] = "${scoreBoard[row][col - 1]}${board[row][col]}"
                        } else {
                            scoreBoard[row][col] = "${board[row][col]}"
                        }

                        if (col == m - 1 || mask[row][col + 1] == '1') {
                            maxScores[Pair(row, col)] = scoreBoard[row][col].toInt()
                        }
                    }

                    else -> { // 세로 형태일 경우
                        if (row != 0 && mask[row - 1][col] == '1') {
                            scoreBoard[row][col] = "${scoreBoard[row - 1][col]}${board[row][col]}"
                        } else {
                            scoreBoard[row][col] = "${board[row][col]}"
                        }

                        if (row == n - 1 || mask[row + 1][col] == '0') {
                            maxScores[Pair(row, col)] = scoreBoard[row][col].toInt()
                        }
                    }
                }
            }
        }

        return maxScores.values.sum()
    }

    var res = 0
    fun bitMasking(idx: Int, prevList: MutableList<String>) {
        if (idx == n) {
            res = maxOf(res, scoring(prevList))
            return
        }

        val prevLine = prevList[idx]
        bits.forEach {
            prevList[idx] = it
            bitMasking(idx + 1, prevList)
        }
        prevList[idx] = prevLine
    }

    bitMasking(0, MutableList(n) { "0".repeat(m) })
    println(res)
}