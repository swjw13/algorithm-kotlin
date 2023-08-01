package week30

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private class Solution9466 {

    fun dfs(curPoint: Int, lst: List<Int>, team: MutableList<Int>): Int {
        return if (team[curPoint] != 0) {
            team[curPoint]
        } else {
            team[curPoint] = curPoint
            val nextTeam = dfs(lst[curPoint - 1], lst, team)
            if (nextTeam == -1){
                team[curPoint] = 0
                -1
            } else {
                team[curPoint] = -1
                if (nextTeam == curPoint) -1
                else nextTeam
            }

        }
    }

    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))
        
        val t = br.readLine().toInt()
        for (i in 0 until t) {
            val n = br.readLine().toInt()

            var cnt = 0
            val lst = br.readLine().split(" ").map { it.toInt() }
            val team = MutableList(n + 1) { 0 }
            for (j in 1 .. n){
                if (lst[j - 1] == j) team[j] = -1
            }

            for (j in 1..n) {
                if (team[j] == 0) {
                    dfs(j, lst, team)
                }
            }

            for (j in 1 until team.size){
                if (team[j] == 0) cnt += 1
            }
            bw.write("$cnt\n")
        }
        bw.flush()
        bw.close()
    }
}

fun main() {
    Solution9466().solution()
}