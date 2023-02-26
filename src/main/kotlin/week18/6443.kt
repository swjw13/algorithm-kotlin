package week18

fun String.sort(): String = this.toList().sorted().joinToString("")

private class Solution6443 {

    val charMap = MutableList(26) { 0 }

    fun backtracking(prev: String, length: Int) {
        if (prev.length == length){
            println(prev)
            return
        }

        for (idx in 0 until 26){
            if (charMap[idx] > 0){
                charMap[idx] -= 1
                backtracking(prev + ('a'.code + idx).toChar(), length)
                charMap[idx] += 1
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        for (i in 0 until n) {
            val word = readln().trim().sort()

            for (idx in 0 until 26) charMap[idx] = 0
            word.forEach {
                charMap[it.code - 'a'.code] += 1
            }

            backtracking("", word.length)
        }
    }
}

fun main() {
    Solution6443().solution()
}