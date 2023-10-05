package week31

private class Solution12784 {

    val dynamite = List(1001) { MutableList(1001) { 0 } }
    val visited = MutableList(1001) { false }

    fun dfs(curPoint: Int, maxPoint: Int): Int {
        var tmp = 0
        for (i in 1..maxPoint) {
            if (!visited[i] && dynamite[curPoint][i] != 0) {
                visited[i] = true
                val result = dfs(i, maxPoint)
                tmp += if (result == 0) dynamite[curPoint][i] else minOf(result, dynamite[curPoint][i])
            }
        }
        return tmp
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readln().trim().toInt()
        for (testCase in 0 until t) {

            val (n, m) = readln().trim().split(" ").map { it.toInt() }
            for (i in 1..n) {
                for (j in 1..n) dynamite[i][j] = 0
                visited[i] = false
            }

            for (i in 0 until m) {
                val (n1, n2, d) = readln().split(" ").map { it.toInt() }
                dynamite[n1][n2] = d
                dynamite[n2][n1] = d
            }

            visited[1] = true
            var res = 0
            for (i in 1..n) {
                if (!visited[i] && dynamite[1][i] != 0) {
                    visited[i] = true
                    val result = dfs(i, n)
                    res += if (result == 0) dynamite[1][i] else minOf(result, dynamite[1][i])
                }
            }
            println(res)
        }
    }
}

fun main() {
    Solution12784().solution()
}
