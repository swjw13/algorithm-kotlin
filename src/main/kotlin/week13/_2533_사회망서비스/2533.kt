package week13._2533_사회망서비스

fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    val tree = Array(n + 1) { mutableListOf<Int>() }
    val startPoint = 1

    for (i in 0 until n - 1) {
        val (start, end) = readln().split(" ").map { it.toInt() }

        tree[start].add(end)
        tree[end].add(start)
    }

    val dp = Array(n + 1) { arrayOf(0, 1) }
    val visited = BooleanArray(n + 1) { false }

    fun dfs(point: Int = startPoint) {
        tree[point].forEach { conn ->
            if (visited[conn].not()) {
                visited[conn] = true
                dfs(conn)
                dp[point][0] += dp[conn][1]
                dp[point][1] += dp[conn].minOf { it }
            }
        }
    }

    visited[startPoint] = true
    dfs()

    println(dp[startPoint].minOf { it })
}