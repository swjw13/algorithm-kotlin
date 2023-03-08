package week20

private class Solution1034 {

    private val board = mutableListOf<MutableList<Int>>()

    fun solution() = with(System.`in`.bufferedReader()){
        var res = 0

        val (n, m) = readln().split(" ").map { it.toInt() }
        val rowMap = mutableMapOf<String, Int>()

        for (i in 0 until n){
            val line = readln().trim()
            if (line in rowMap.keys){
                rowMap[line] = rowMap[line]!! + 1
            } else {
                rowMap[line] = 1
            }
        }

        val k = readln().toInt()

        for (key in rowMap.keys){
            val zeroCnt = key.count { it == '0' }
            if (zeroCnt <= k && zeroCnt % 2 == k % 2){
                res = maxOf(res, rowMap[key]!!)
            }
        }

        println(res)
    }
}

fun main() {
    Solution1034().solution()
}