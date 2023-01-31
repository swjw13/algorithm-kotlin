package week15._1405_미친로봇

data class Point(
    var row: Int,
    var col: Int
)

inline fun String.mulOf(selector: (Char) -> Double): Double {
    var mul: Double = 1.toDouble()
    for (element in this) {
        mul *= selector(element)
    }
    return mul
}

fun main() = with(System.`in`.bufferedReader()) {
    val (N, e, w, s, n) = readln().split(" ").map { it.toInt() }
    val available = mutableSetOf<String>()
    listOf(e, w, s, n).forEachIndexed { index, i ->
        if (i != 0) available.add("EWSN"[index].toString())
    }

    var res = 0.0
    val movement = mapOf("E" to Point(0, 1), "W" to Point(0, -1), "N" to Point(-1, 0), "S" to Point(1, 0))
    val visited = List(30) { MutableList(30) { false } }
    visited[15][15] = true

    fun calculatePercent(path: String): Double {
        return path.mulOf {
            when (it) {
                'E' -> e / 100.toDouble()
                'W' -> w / 100.toDouble()
                'N' -> n / 100.toDouble()
                'S' -> s / 100.toDouble()
                else -> 0 / 100.toDouble()
            }
        }
    }

    fun dfs(prevString: String, prevRow: Int, prevCol: Int) {
        if (prevString.length == N) res += calculatePercent(prevString)
        else {
            available.forEach { availableMove ->
                movement[availableMove]?.let { movementEach ->
                    val newRow = prevRow + movementEach.row
                    val newCol = prevCol + movementEach.col
                    if (visited[newRow][newCol].not()) {
                        visited[newRow][newCol] = true
                        dfs(prevString + availableMove, newRow, newCol)
                        visited[newRow][newCol] = false
                    }
                }
            }
        }
    }

    dfs("", 15, 15)
    println(res)
}