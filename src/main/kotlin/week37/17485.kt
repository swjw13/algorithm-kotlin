package week37

private object Solution17485 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readln().split(" ").map { it.toInt() }

        val board = mutableListOf<List<Int>>()
        for (i in 0 until n) {
            val line = readln().split(" ").map { it.toInt() }
            board.add(line)
        }

        val dp = List(n) { List(m) { MutableList(3) { -1 } } }
        for (j in 0 until m){
            for (k in 0 until 3){
                dp[0][j][k] = board[0][j]
            }
        }

        for (row in 0 until n - 1){
            for (col in 0 until m){
                for (direction in 0 until 3){
                    for (tempDirection in 0 until 3){
                        if (direction != tempDirection && dp[row][col][direction] != -1){
                            val newRow = row + 1
                            val newCol = col + tempDirection - 1

                            if (newCol < 0 || newCol >= m) continue

                            val newValue = dp[row][col][direction] + board[newRow][newCol]

                            if (dp[newRow][newCol][tempDirection] == -1 ){
                                dp[newRow][newCol][tempDirection] = newValue
                            } else if (dp[newRow][newCol][tempDirection] > newValue){
                                dp[newRow][newCol][tempDirection] = newValue
                            }
                        }
                    }
                }
            }
        }

        println(dp.last().flatten().filter { it != -1 }.minOf { it })
    }
}

fun main() {
    Solution17485.solution()
}