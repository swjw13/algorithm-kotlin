package week26

import java.util.TreeSet

private class Solution23326 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val treeSet = TreeSet<Int>()
        readln().split(" ").map { it.toInt() }.forEachIndexed { index, i ->
            if (i == 1) treeSet.add(index)
        }

        var idx = 0L
        for (i in 0 until m) {
            val query = readln().trim()
            if (query[0] == '3') {
                if (treeSet.isEmpty()) {
                    println(-1)
                    continue
                }
                val upper = treeSet.ceiling(idx.toInt())
                val lower = treeSet.first()
                if (upper == null) {
                    println(n + lower - idx)
                } else {
                    println(upper - idx)
                }
            } else {
                val (queryNumber, queryAction) = query.split(" ").map { it.toLong() }
                if (queryNumber == 2L) {
                    idx = (idx + queryAction) % n
                } else if (queryNumber == 1L) {
                    val realAction = queryAction.toInt() - 1
                    if (realAction in treeSet) {
                        treeSet.remove(realAction)
                    } else {
                        treeSet.add(realAction)
                    }
                }
            }
        }
    }
}

fun main() {
    Solution23326().solution()
}