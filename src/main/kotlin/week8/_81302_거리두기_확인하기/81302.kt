package week8._81302_거리두기_확인하기

data class Point(
    val row: Int,
    val col: Int,
    val dist: Int
)

class Solution {

    val dxy = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

    fun solution(places: Array<Array<String>>): IntArray {

        fun check(board: Array<String>): Boolean {
            for (row in board.indices) {
                for (col in 0 until board[0].length) {
                    if (board[row][col] == 'P') {
                        val visited = List(board.size) { MutableList(board[0].length) { false } }
                        visited[row][col] = true

                        val queue = ArrayDeque<Point>()
                        queue.add(Point(row, col, 0))

                        while (queue.isNotEmpty()) {
                            val curPoint = queue.removeFirst()
                            val curRow = curPoint.row
                            val curCol = curPoint.col
                            val curDist = curPoint.dist

                            if (board[curRow][curCol] == 'P' && (curRow == row && curCol == col).not()) return false

                            if (curDist + 1 <= 2) {
                                for (movement in dxy) {
                                    val newRow = curRow + movement.first
                                    val newCol = curCol + movement.second

                                    if (newRow in board.indices
                                        && newCol in 0 until board[0].length
                                        && visited[newRow][newCol].not()
                                        && board[newRow][newCol] != 'X'
                                    ) {
                                        queue.add(Point(newRow, newCol, curDist + 1))
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true
        }

        val answer = mutableListOf<Int>()

        places.forEach {
            if (check(it)) answer.add(1)
            else answer.add(0)
        }

        return answer.toIntArray()
    }
}