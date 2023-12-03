package week35

private class Solution1322 {

    fun numToBin(num: Long): String {
        var res = ""
        var tmp = num
        while (tmp > 0) {
            res = "${tmp % 2}${res}"
            tmp /= 2
        }
        return res
    }

    fun binToNum(bin: String): Long {
        var tmp = 1L
        var res = 0L
        for (idx in bin.length - 1 downTo 0) {
            res += bin[idx].digitToInt() * tmp
            tmp *= 2
        }

        return res
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (x, k) = readln().split(" ").map { it.toLong() }
        val kBin = numToBin(k).toMutableList()
        val xBin = numToBin(x).toMutableList()

        var res = ""
        for (idx in xBin.size - 1 downTo 0) {
            if (kBin.isEmpty()) break
            res = if (xBin[idx] == '0') {
                val curK = kBin.removeLast()
                "${curK}${res}"
            } else {
                "${'0'}${res}"
            }
        }

        for (remain in kBin.size - 1 downTo 0){
            res = "${kBin[remain]}${res}"
        }

        println(binToNum(res))
    }
}

fun main() {
    Solution1322().solution()
}