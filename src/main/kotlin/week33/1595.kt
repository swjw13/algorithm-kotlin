package week33

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


private object Solution1595 {
    var answer = 0
    val visited = MutableList(10001) { false }
    val connected = MutableList(10001) { mutableMapOf<Int, Int>() }

    fun dfs(city: Int): Int {
        val connectedCity = connected[city].keys.toList()
        val dists = mutableListOf<Int>()
        for (c in connectedCity) {
            if (visited[c]) continue
            visited[c] = true
            dists.add(dfs(c) + connected[city][c]!!)
        }

        dists.sortDescending()
        return if (dists.size == 0) {
            0
        } else if (dists.size == 1) {
            answer = maxOf(answer, dists[0])
            dists[0]
        } else {
            answer = maxOf(answer, dists[0] + dists[1])
            dists[0]
        }
    }

    fun solution() {
        try {
            BufferedReader(InputStreamReader(System.`in`)).use { br ->
                var st: StringTokenizer
                var line: String?
                while (!br.readLine().also { line = it }.isEmpty()) {
                    st = StringTokenizer(line)
                    val a = st.nextToken().toInt()
                    val b = st.nextToken().toInt()
                    val v = st.nextToken().toInt()

                    if (connected[a].containsKey(b)) {
                        connected[a][b] = maxOf(connected[a][b]!!, v)
                    } else {
                        connected[a][b] = v
                    }

                    if (connected[b].containsKey(a)) {
                        connected[b][a] = maxOf(connected[b][a]!!, v)
                    } else {
                        connected[b][a] = v
                    }
                }
            }
        } catch (_: Exception) {
        }

        visited[1] = true
        dfs(1)
        println(answer)

    }
}

fun main() {
    Solution1595.solution()
}