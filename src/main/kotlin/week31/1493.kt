package week31

import kotlin.math.ln
import kotlin.math.pow

private class Solution1493 {
    val cubeCount = MutableList(20) { 0 }

    fun divide(r: Int, c: Int, h: Int): Int {
        if (r == 0 || c == 0 || h == 0) return 0
        if (r == 1 || c == 1 || h == 1) {
            val tmp: Long = r.toLong() * c * h
            return if (tmp > Int.MAX_VALUE || cubeCount[0] < tmp) -1
            else {
                cubeCount[0] -= tmp.toInt()
                tmp.toInt()
            }
        }

        var minimumLength = minOf((ln(minOf(r, c, h).toDouble()) / ln(2.0)).toInt(), 20)
        while (minimumLength >= 0 && cubeCount[minimumLength] == 0) minimumLength -= 1

        return if (minimumLength == -1) {
            -1
        } else {
            val l = 2.0.pow(minimumLength.toDouble()).toInt()
            cubeCount[minimumLength] -= 1
            val first = divide(r - l, l, h)
            val second = divide(l, c, h - l)
            val third = divide(r, c - l, l)
            val last = divide(r - l, c - l, h - l)

            if (first == -1 || second == -1 || third == -1 || last == -1) -1
            else first + second + third + last + 1
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (length, width, height) = readln().split(" ").map { it.toInt() }
        val n = readln().toInt()

        for (i in 0 until n) {
            val (a, b) = readln().split(" ").map { it.toInt() }
            cubeCount[a] += b
        }

        println(divide(length, width, height))
    }
}

fun main() {
    Solution1493().solution()
}