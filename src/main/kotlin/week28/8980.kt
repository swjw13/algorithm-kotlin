package week28

private class Solution8980 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, c) = readln().split(" ").map { it.toInt() }
        val m = readln().toInt()
        val buses = mutableListOf<List<Int>>()
        for (i in 0 until m) {
            buses.add(readln().split(" ").map { it.toInt() })
        }
        buses.sortWith(compareBy({ it[1] }, { it[0] }))

        val weights = MutableList(n + 1) { 0 }
        var answer = 0

        for ((start, end, w) in buses) {
            var tmp = w
            for (i in start until end){
                tmp = minOf(tmp, c - weights[i])
            }

            answer += tmp
            for (i in start until end){
                weights[i] += tmp
            }
        }

        println(answer)
    }
}

fun main() {
    Solution8980().solution()
}