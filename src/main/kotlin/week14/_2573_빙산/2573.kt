package week14._2573_빙산

val dxdy = arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))

enum class ResultCode {
    ALL_ZERO, SPLIT, NO_ERROR
}

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = mutableListOf<MutableList<Int>>()
    for (i in 0 until n) {
        val line = readln().split(" ").map { it.toInt() }.toMutableList()
        map.add(line)
    }

    var turn = 1
    while (true) {
        countSea(map, n, m)
        when (bfs(map, n, m)) {
            ResultCode.ALL_ZERO -> {
                println(0)
                break
            }

            ResultCode.SPLIT -> {
                println(turn)
                break
            }

            ResultCode.NO_ERROR -> {
                turn += 1
            }
        }
    }
}

fun List<Int>.copy(): MutableList<Int> {
    return MutableList(this.size) { this[it] }
}

fun bfs(lst: MutableList<MutableList<Int>>, n: Int, m: Int): ResultCode {
    val tmp = MutableList(n) { lst[it].copy() }
    var isEmpty = true

    for (row in 0 until n) {
        for (col in 0 until m) {
            if (tmp[row][col] != 0) {
                isEmpty = false

                val queue = ArrayDeque<Pair<Int, Int>>().apply { this.add(Pair(row, col)) }
                tmp[row][col] = 0
                while (queue.isNotEmpty()) {
                    val curPoint = queue.removeFirst()
                    for (movement in dxdy) {
                        val newRow = curPoint.first + movement.first
                        val newCol = curPoint.second + movement.second
                        if (newRow in 0 until n && newCol in 0 until m && tmp[newRow][newCol] != 0) {
                            tmp[newRow][newCol] = 0
                            queue.add(Pair(newRow, newCol))
                        }
                    }
                }
                break
            }
        }
        if (isEmpty.not()) break
    }

    if (isEmpty) return ResultCode.ALL_ZERO
    for (row in 0 until n) {
        for (col in 0 until m) {
            if (tmp[row][col] != 0) return ResultCode.SPLIT
        }
    }
    return ResultCode.NO_ERROR
}

fun countSea(lst: MutableList<MutableList<Int>>, n: Int, m: Int) {
    val res = MutableList(n) { MutableList(m) { 0 } }
    for (row in 0 until n) {
        for (col in 0 until m) {
            var cnt = 0
            for (movement in dxdy) {
                val newRow = row + movement.first
                val newCol = col + movement.second
                if (newRow in 0 until n && newCol in 0 until m && lst[newRow][newCol] == 0) cnt += 1
            }

            res[row][col] = cnt
        }
    }

    for (row in 0 until n) {
        for (col in 0 until m) {
            lst[row][col] = maxOf(0, lst[row][col] - res[row][col])
        }
    }
}