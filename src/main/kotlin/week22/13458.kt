package week22

private class Solution13458 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        val lst = readln().split(" ").map { it.toInt() }
        val (b, c) = readln().split(" ").map { it.toInt() }

        var res = 0.toLong()
        lst.forEach {
            res += 1
            if (it > b) {
                res += (it - b - 1) / c + 1
            }
        }

        println(res)
    }
}

fun main() {
    Solution13458().solution()
}