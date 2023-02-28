package week19

private class Solution2631 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        val numbers = mutableListOf<Int>()
        val notMoving = MutableList(n) { 1 }
        for (i in 0 until n) {
            val curNum = readln().toInt()
            numbers.add(curNum)
            for (j in 0 until i) {
                if (numbers[j] < curNum) {
                    notMoving[i] = maxOf(notMoving[j] + 1, notMoving[i])
                }
            }
        }
        println(n - notMoving.maxOf { it })
    }
}

fun main() {
    Solution2631().solution()
}