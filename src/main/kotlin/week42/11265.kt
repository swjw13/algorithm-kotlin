package week42

private object Solution11265 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val board = mutableListOf<MutableList<Long>>()
        for (i in 0 until n){
            val line = readLine().split(" ").map { it.toLong() }.toMutableList()
            board.add(line)
        }

        for (k in 0 until n){
            for (i in 0 until n){
                for (j in 0 until n){
                    board[i][j] = minOf(board[i][j], board[i][k] + board[k][j])
                }
            }
        }

        for (i in 0 until m){
            val (a, b, c) = readLine().split(" ").map { it.toInt() }
            if (board[a - 1][b - 1] <= c) println("Enjoy other party")
            else println("Stay here")
        }
    }
}

fun main(){
    Solution11265.solution()
}