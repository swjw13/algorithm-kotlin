package week33

private object Solution12970 {

    var answer = "-1"
    var N = -1
    var K = -1

    fun dfs(prevWord: String, prevPoint: Int, prevACount: Int) {
        if (answer != "-1") return
        if (prevPoint == K) {
            var tmp = prevWord
            while (tmp.length < K) tmp += "A"
            answer = tmp
            return
        }
        if (prevWord.length == N) return

        if (prevPoint + prevACount <= K){
            dfs(prevWord + 'B', prevPoint + prevACount, prevACount)
        }

        if (answer != "-1") return
        dfs(prevWord + 'A', prevPoint, prevACount + 1)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        N = n
        K = k

        dfs("A", 0, 1)
        dfs("B", 0, 0)

        println(answer)

    }
}

fun main() {
    Solution12970.solution()
}