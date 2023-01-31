package week15._15683_감시

enum class CameraType {
    ONE, TWO, THREE, FOUR, FIVE
}

val cameraTypeList =
    listOf(CameraType.ONE, CameraType.ONE, CameraType.TWO, CameraType.THREE, CameraType.FOUR, CameraType.FIVE)

operator fun <T> MutableSet<T>.plus(elements: Iterable<T>): MutableSet<T> {
    val result = mutableSetOf<T>()
    result.addAll(this)
    result.addAll(elements)
    return result
}

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val board = mutableListOf<List<Int>>()
    for (i in 0 until n) {
        val line = readln().split(" ").map { it.toInt() }
        board.add(line)
    }

    fun findVision(type: CameraType, row: Int, col: Int): List<MutableSet<Pair<Int, Int>>> {
        val east = mutableSetOf<Pair<Int, Int>>().apply {
            for (i in col until m) {
                if (board[row][i] == 6) break
                add(Pair(row, i))
            }
        }
        val west = mutableSetOf<Pair<Int, Int>>().apply {
            for (i in col downTo 0) {
                if (board[row][i] == 6) break
                add(Pair(row, i))
            }
        }
        val north = mutableSetOf<Pair<Int, Int>>().apply {
            for (i in row downTo 0) {
                if (board[i][col] == 6) break
                add(Pair(i, col))
            }
        }
        val south = mutableSetOf<Pair<Int, Int>>().apply {
            for (i in row until n) {
                if (board[i][col] == 6) break
                add(Pair(i, col))
            }
        }

        return when (type) {
            CameraType.ONE -> {
                listOf(east, west, north, south)
            }

            CameraType.TWO -> {
                val vertical = east + west
                val horizontal = north + south
                listOf(vertical, horizontal)
            }

            CameraType.THREE -> {
                val firstQuadrant = east + north
                val secondQuadrant = north + west
                val thirdQuadrant = west + south
                val fourthQuadrant = south + east

                listOf(firstQuadrant, secondQuadrant, thirdQuadrant, fourthQuadrant)
            }

            CameraType.FOUR -> {
                val noEast = west + north + south
                val noWest = east + north + south
                val noNorth = east + west + south
                val noSouth = east + west + north

                listOf(noEast, noWest, noSouth, noNorth)
            }

            CameraType.FIVE -> {
                listOf(east + west + north + south)
            }
        }
    }

    val cameraList = mutableListOf<List<MutableSet<Pair<Int, Int>>>>()
    val visibleSet = mutableListOf<Int>()
    val wallSet = mutableSetOf<Pair<Int, Int>>()
    var res = 0

    for (row in 0 until n) {
        for (col in 0 until m) {
            if (board[row][col] in 1..5) cameraList.add(findVision(cameraTypeList[board[row][col]], row, col))
            else if (board[row][col] == 6) wallSet.add(Pair(row, col))
        }
    }

    fun dfs(turn: Int) {
        if (turn == cameraList.size) {
            val tmp = mutableSetOf<Pair<Int, Int>>()
            visibleSet.forEachIndexed { index, i ->
                tmp += cameraList[index][i]
            }
            tmp.addAll(wallSet)
            res = maxOf(res, tmp.size)
            return
        }

        for (i in 0 until cameraList[turn].size) {
            visibleSet.add(i)
            dfs(turn + 1)
            visibleSet.removeLast()
        }
    }

    dfs(0)
    println(n * m - res)
}

