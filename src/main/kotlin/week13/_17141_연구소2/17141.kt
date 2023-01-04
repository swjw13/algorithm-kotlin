package week13._17141_연구소2

data class Point(
    val row: Int,
    val col: Int
)

data class QueueElement(
    val point: Point,
    val turn: Int
)

fun main() = with(System.`in`.bufferedReader()) {

    var res = Int.MAX_VALUE
    val movementList = listOf(Point(-1, 0), Point(1, 0), Point(0, -1), Point(0, 1))

    val (n, m) = readln().split(" ").map { it.toInt() }
    val board = mutableListOf<List<Int>>()
    val virusList = mutableListOf<Point>()
    val pickedNum = mutableListOf<Int>()

    for (row in 0 until n) {
        val line = readln().split(" ").map { it.toInt() }

        for (col in 0 until n) {
            if (line[col] == 2) virusList.add(Point(row, col))
        }

        board.add(line)
    }

    fun bfs() {
        val curBoard = board.map { it.copy() }
        val queue = ArrayDeque<QueueElement>()

        var tmp = 0

        (0 until virusList.size).forEach {
            if (it in pickedNum){
                queue.add(QueueElement(virusList[it], 0))
            } else {
                curBoard[virusList[it].row][virusList[it].col] = 0
            }
        }

        while (queue.isNotEmpty()) {
            val curElement = queue.removeFirst()

            for (movement in movementList) {
                val newRow = curElement.point.row + movement.row
                val newCol = curElement.point.col + movement.col

                if (newRow in 0 until n && newCol in 0 until n) {
                    if (curBoard[newRow][newCol] == 0) {
                        curBoard[newRow][newCol] = curElement.turn + 1
                        tmp = maxOf(tmp, curElement.turn + 1)
                        queue.add(QueueElement(Point(newRow, newCol), curElement.turn + 1))
                    }
                }
            }
        }

        var isAll = false
        for (line in curBoard) {
            if (0 in line) {
                isAll = true
                break
            }
        }
        if (isAll.not()) res = minOf(res, tmp)
    }

    fun combination(cnt: Int, beginWith: Int) {
        if (cnt == m) {
            bfs()
            return
        }

        for (index in beginWith until virusList.size) {
            pickedNum.add(index)
            combination(cnt + 1, index + 1)
            pickedNum.removeLast()
        }
    }

    combination(0, 0)
    if (res == Int.MAX_VALUE) println(-1)
    else println(res)
}

fun <T> List<T>.copy(): MutableList<T> {
    val res = mutableListOf<T>()
    this.forEach { res.add(it) }
    return res
}