package week32

/**
 * DFS 사용
 * N의 크기가 100 이므로 100 * 100 으로 찾아도 문제가 없을 것이라 생각
 * 숫자 하나하나 비교를 하는 방법으로 구함
 */

private class Solution16936 {
    val visited = mutableListOf<Int>()
    lateinit var lst: List<Long>
    var n: Int = 0

    fun dfs(curIdx: Int, cnt: Int): Boolean {
        if (cnt == n) {
            return true
        }

        val isDividedByThree = lst[curIdx] % 3 == 0L
        for (i in 0 until n) {
            if (i != curIdx) {
                if (isDividedByThree) {
                    if (lst[i] == lst[curIdx] / 3) {
                        visited.add(i)
                        val result = dfs(i, cnt + 1)
                        if (result) return true
                        visited.removeLast()
                    }
                }

                if (lst[i] == lst[curIdx] * 2) {
                    visited.add(i)
                    val result = dfs(i, cnt + 1)
                    if (result) return true
                    visited.removeLast()
                }
            }
        }
        return false
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        n = readln().toInt()
        lst = readln().split(" ").map { it.toLong() }

        for (i in 0 until n) {
            visited.add(i)
            val result = dfs(i, 1)
            if (result) break
            visited.removeLast()
        }

        println(visited.joinToString(" ") {
            lst[it].toString()
        })
    }
}

fun main() {
    Solution16936().solution()
}