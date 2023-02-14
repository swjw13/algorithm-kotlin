package week16._2608_로마숫자

import java.lang.StringBuilder

operator fun String.times(num: Int): String {
    val sb = StringBuilder()
    for (i in 0 until num) sb.append(this)
    return sb.toString()
}

fun main() = with(System.`in`.bufferedReader()) {
    val romToNumMap = mutableMapOf("I" to 1, "V" to 5, "X" to 10, "L" to 50, "C" to 100, "D" to 500, "M" to 1000)

    fun numToRom(num: Int): String {
        var tmp = num
        val sb = StringBuilder()
        if (tmp / 1000 != 0) {
            sb.append("M" * (tmp / 1000))
            tmp %= 1000
        }

        if (tmp / 100 != 0) {
            when (tmp / 100) {
                in 1..3 -> sb.append("C" * (tmp / 100))
                4 -> sb.append("CD")
                in 5..8 -> sb.append("D" + "C" * (tmp / 100 - 5))
                else -> sb.append("CM")
            }
            tmp %= 100
        }

        if (tmp / 10 != 0) {
            when (tmp / 10) {
                in 1..3 -> sb.append("X" * (tmp / 10))
                4 -> sb.append("XL")
                in 5..8 -> sb.append("L" + "X" * (tmp / 10 - 5))
                else -> sb.append("XC")
            }
            tmp %= 10
        }

        if (tmp != 0) {
            when (tmp) {
                in 1..3 -> sb.append("I" * tmp)
                4 -> sb.append("IV")
                in 5..8 -> sb.append("V" + "I" * (tmp - 5))
                else -> sb.append("IX")
            }
        }
        return sb.toString()
    }

    fun romToNum(rom: String): Int {
        var res = 0
        for (i in 0 until rom.length - 1) {
            val myValue = rom[i].toString()
            val nextValue = rom[i + 1].toString()

            if (romToNumMap[myValue]!! >= romToNumMap[nextValue]!!) res += romToNumMap[myValue]!!
            else res -= romToNumMap[myValue]!!
        }
        res += romToNumMap[rom.last().toString()]!!

        return res
    }

    val first = readln()
    val second = readln()

    val total = romToNum(first) + romToNum(second)
    println(total)
    println(numToRom(total))
}