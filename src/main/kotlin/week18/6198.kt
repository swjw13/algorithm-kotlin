package week18

import java.util.Stack

data class Height(
    val value: Int,
    val idx: Int
)

private class Solution6198 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        val stack = Stack<Height>()
        val res = MutableList(n + 1) { 0.toLong() }


        for (idx in 0 until n) {
            val newHeight = readln().toInt()
            while (stack.isNotEmpty() && stack.last().value <= newHeight){
                val poppedHeight = stack.pop()
                res[poppedHeight.idx] = (idx - poppedHeight.idx - 1).toLong()
            }
            stack.add(Height(newHeight, idx))
        }

        while (stack.isNotEmpty()){
            val poppedHeight = stack.pop()
            res[poppedHeight.idx] = (n - poppedHeight.idx - 1).toLong()
        }

        println(res.sum())
    }
}

fun main() {
    Solution6198().solution()
}