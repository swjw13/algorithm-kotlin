package week40

private object Solution2239 {

    val pointToWrite = mutableListOf<Point>()
    val board = mutableListOf<MutableList<Int>>()
    var n: Int = 0
    var result: String = ""
    val allIndexRange = 0 until 9
    val allNumberRange = 1 .. 9

    data class Point(
        val row: Int,
        val col: Int
    )

    fun solution() = with(System.`in`.bufferedReader()) {
        for (row in 0 until 9) {
            val line = readln().trim().toList().map { it.toString().toInt() }.toMutableList()
            for (col in 0 until 9) {
                if (line[col] == 0) {
                    pointToWrite.add(Point(row, col))
                }
            }
            board.add(line)
        }
        n = pointToWrite.size

        dfs(0)
        println(result.chunked(9).joinToString("\n"))
    }

    fun dfs(curIdx: Int): Boolean {
        if (curIdx == n) {
            updateResult()
            return true
        }

        val (curRow, curCol) = pointToWrite[curIdx]

        val rowRange = getRange(curRow)
        val colRange = getRange(curCol)

        val tmp = MutableList(10) { true }

        for (row in allIndexRange){
            tmp[board[row][curCol]] = false
        }

        for (col in allIndexRange){
            tmp[board[curRow][col]] = false
        }

        for (row in rowRange){
            for (col in colRange){
                tmp[board[row][col]] = false
            }
        }

        allNumberRange
            .asSequence()
            .filter { tmp[it] }
            .forEach {
                board[curRow][curCol] = it
                val result = dfs(curIdx + 1)
                if (result) return true
                board[curRow][curCol] = 0
            }
        return false
    }

    fun updateResult() {
        val curResult = board.joinToString(
            separator = "",
            transform = { lst -> lst.joinToString("") }
        )

        result = maxOf(result, curResult)
    }

    fun getRange(curIdx: Int): IntRange {
        return if (curIdx < 3) 0..2
        else if (curIdx >= 6) 6..8
        else 3..5
    }
}

fun main() {
    Solution2239.solution()
}