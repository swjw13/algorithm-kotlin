package week13._6593_상범빌딩

data class Point(
    val floor: Int,
    val row: Int,
    val column: Int
) {
    fun match(otherPoint: Point): Boolean = (floor == otherPoint.floor && row == otherPoint.row && column == otherPoint.column)
}

data class QueueElement(
    val point: Point,
    val dist: Int
)

fun main() = with(System.`in`.bufferedReader()) {
    val movementList = listOf(
        Point(-1, 0, 0),
        Point(1, 0, 0),
        Point(0, -1, 0),
        Point(0, 1, 0),
        Point(0, 0, -1),
        Point(0, 0, 1)
    )

    while (true) {
        val (l, r, c) = readln().split(" ").map { it.toInt() }
        if (l == 0 && r == 0 && c == 0) break

        var startPoint = Point(-1, -1, -1)
        var endPoint = Point(-1, -1, -1)
        val board = mutableListOf<List<List<String>>>()

        val queue = ArrayDeque<QueueElement>()
        val visited = mutableSetOf<Point>()

        for (floor in 0 until l) {
            val oneFloor = mutableListOf<List<String>>()
            for (row in 0 until r) {
                val oneRow = readln().toList().map { it.toString() }

                if ("S" in oneRow) {
                    startPoint = Point(floor, row, oneRow.indexOf("S"))
                }

                if ("E" in oneRow) {
                    endPoint = Point(floor, row, oneRow.indexOf("E"))
                }

                oneFloor.add(oneRow)
            }
            board.add(oneFloor)
            readln()
        }

        queue.add(QueueElement(startPoint, 0))
        visited.add(startPoint)

        var isEscapable = -1

        while (queue.isNotEmpty()) {
            val curElement = queue.removeFirst()
            if (curElement.point.match(endPoint)) {
                isEscapable = curElement.dist
                break
            }

            for (movement in movementList){
                val newFloor = curElement.point.floor + movement.floor
                val newRow = curElement.point.row + movement.row
                val newCol = curElement.point.column + movement.column

                val newPoint = Point(newFloor, newRow, newCol)
                if (newFloor in 0 until l && newRow in 0 until r && newCol in 0 until c) {
                    if (newPoint !in visited && board[newFloor][newRow][newCol] != "#") {
                        visited.add(newPoint)
                        queue.add(QueueElement(newPoint, curElement.dist + 1))
                    }
                }
            }
        }
        if (isEscapable != -1) println("Escaped in $isEscapable minute(s).")
        else println("Trapped!")
    }
}