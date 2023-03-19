package week21

import kotlin.math.pow

private class Solution1062 {
    val alphaList = "bdefghjklmopqrsuvwxyz".toList()

    /**
     * 2**22 = 약 400만
     */
    val maxMask = 2.0.pow(22.0).toInt() - 1

    fun countOne(num: Int): Int {
        var numTmp = num
        var cnt = 0
        while (numTmp != 0) {
            cnt += (numTmp % 2)
            numTmp /= 2
        }
        return cnt
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        if (k < 5) {
            println(0)
            return@with
        }

        val bitMaskList = mutableListOf<Int>()
        for (i in 0 until n) {
            val curWord = readln().trim()
            var bitMask = 0
            for (alpha in alphaList) {
                if (alpha in curWord) bitMask += 1
                bitMask *= 2
            }
            bitMaskList.add(bitMask)
        }

        if (k == 5) {
            println(bitMaskList.filter { it == 0 }.size)
            return@with
        }

        var res = 0
        for (i in 1..maxMask) {
            if (countOne(i) == k - 5) {
                res = maxOf(res, bitMaskList.count { it and i == it })
            }
        }
        println(res)
    }
}

fun main() {
    Solution1062().solution()
}
