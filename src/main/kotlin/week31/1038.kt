package week31

private class Solution1038 {

    val downNumberList = mutableListOf<Long>()

    fun backTracking(curNum: Int, prev: String) {
        downNumberList.add(prev.toLong())
        for (i in curNum - 1 downTo 0) {
            backTracking(i, "${prev}${i}")
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()

        for (i in 0 until 10) {
            backTracking(i, i.toString())
        }

        downNumberList.sort()

        if (n >= downNumberList.size) {
            println(-1)
        } else {
            println(downNumberList[n])
        }
    }
}

fun main() {
    Solution1038().solution()
}