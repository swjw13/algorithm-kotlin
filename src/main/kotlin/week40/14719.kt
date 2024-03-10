package week40

private object Solution14719 {

    fun solution() = with(System.`in`.bufferedReader()) {
        val (h, w) = readln().split(" ").map { it.toInt() }
        val heights = readln().split(" ").map { it.toInt() }

        val board = List(h) { MutableList(w) { 0 } }
        for (col in 0 until w) {
            for (row in 0 until heights[col]) {
                board[row][col] = 1
            }
        }

        var result = 0
        for (row in 0 until h) {
            var isIn = false
            var dist = 0
            for (col in 0 until w - 1) {
                val first = board[row][col]
                val second = board[row][col + 1]

                when {
                    first == 0 && second == 0 -> {
                        if (isIn) {
                            dist += 1
                        }
                    }

                    first == 0 && second == 1 -> {
                        if (isIn) {
                            result += dist + 1
                            dist = 0
                        }
                        isIn = false
                    }

                    first == 1 && second == 0 -> {
                        isIn = true
                    }
                }
            }
        }
        println(result)
    }
}

fun main() {
    Solution14719.solution()
}

