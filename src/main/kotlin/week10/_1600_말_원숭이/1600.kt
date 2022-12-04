package week10._1600_말_원숭이

fun main() = with(System.`in`.bufferedReader()) {

    val defaultMovement = listOf(listOf(-1, 0), listOf(1, 0), listOf(0, -1), listOf(0, 1))
    val horseMovement =
        listOf(
            listOf(-2, 1),
            listOf(-2, 1),
            listOf(-1, 2),
            listOf(-1, -2),
            listOf(1, 2),
            listOf(1, -2),
            listOf(2, -1),
            listOf(2, 1)
        )

    val k = readln().toInt()
    val (w, h) = readln().split(" ").map { it.toInt() }

    val board = mutableListOf<MutableList<Int>>()
    for (i in 0 until h) {
        board.add(readln().split(" ").map { it.toInt() }.toMutableList())
    }

    val visited = List(k + 1) { List(h) { MutableList(w) { false } } }

    val queue = ArrayDeque<List<Int>>()
    queue.add(listOf(0, 0, k, 0))
    visited[k][0][0] = true

    while (queue.isNotEmpty()) {
        val (curRow, curCol, remainChance, count) = queue.removeFirst()
        if (curRow == h - 1 && curCol == w - 1) {
            println(count)
            return@with
        }

        for (default in defaultMovement) {
            val newRow = curRow + default[0]
            val newCol = curCol + default[1]

            if (newRow in 0 until h && newCol in 0 until w && board[newRow][newCol] != 1 && visited[remainChance][newRow][newCol].not()) {
                visited[remainChance][newRow][newCol] = true
                queue.add(listOf(newRow, newCol, remainChance, count + 1))
            }
        }

        for (horse in horseMovement) {
            val newRow = curRow + horse[0]
            val newCol = curCol + horse[1]

            if (newRow in 0 until h && newCol in 0 until w && board[newRow][newCol] != 1 && remainChance > 0 && visited[remainChance - 1][newRow][newCol].not()) {
                visited[remainChance - 1][newRow][newCol] = true
                queue.add(listOf(newRow, newCol, remainChance - 1, count + 1))
            }
        }
    }
    println(-1)
}