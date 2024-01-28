package week36

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private object Solution20955 {
    private val parent = mutableMapOf<Int, Int>()
    fun solution() {
        var cnt = 0
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))

        val (n, m) = readln().split(" ").map { it.toInt() }
        for (i in 1..n) {
            parent[i] = i
        }

        repeat(m) {
            val token = StringTokenizer(br.readLine())
            val a = token.nextToken().toInt()
            val b = token.nextToken().toInt()

            if(!union(a, b)) cnt += 1
        }

        for (i in 2..n) {
            if (union(1, i)) cnt += 1
        }

        println(cnt)

        bw.flush()
        bw.close()
    }

    private fun find(curNum: Int): Int {
        if (parent[curNum] != curNum) {
            parent[curNum] = find(parent[curNum]!!)
        }

        return parent[curNum]!!
    }

    private fun union(point1: Int, point2: Int): Boolean {
        val parent1 = find(point1)
        val parent2 = find(point2)

        if (parent1 == parent2) return false

        parent[parent2] = parent1
        return true
    }
}

fun main() {
    Solution20955.solution()
}