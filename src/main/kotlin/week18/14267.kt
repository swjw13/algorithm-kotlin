package week18

private class Solution14267 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val higherList = readln().split(" ").map { it.toInt() }
        val res = MutableList(n) { 0 }

        for (idx in 0 until m){
            val (i, w) = readln().split(" ").map { it.toInt() }
            res[i - 1] += w
        }

        for (i in 1 until  n){
            res[i] += res[higherList[i] - 1]
        }

        println(res.joinToString(" "))
    }
}

fun main() {
    Solution14267().solution()
}