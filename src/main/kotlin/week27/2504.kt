package week27

import java.lang.Exception

/**
 * Stack으로 해결했는데
 * Regex로 replace하면서 풀면 어땠을까 하는 생각
 */

private class Solution2504 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val word = readln().trim()
        val length = word.length
        val stack = ArrayDeque<String>()
        var tmp = 0

        try {
            for (i in 0 until length) {
                when (word[i]) {
                    '[' -> {
                        stack.add("[")
                    }

                    '(' -> {
                        stack.add("(")
                    }

                    ']' -> {
                        while (stack.isNotEmpty() && stack.last().matches(Regex("\\d+"))) {
                            tmp += stack.removeLast().toInt()
                        }

                        if (stack.isEmpty() || stack.last() != "[") {
                            println(0)
                            return@with
                        }
                        stack.removeLast()
                        if (tmp == 0) stack.add("3") else stack.add((tmp * 3).toString())
                        tmp = 0
                    }

                    ')' -> {
                        while (stack.isNotEmpty() && stack.last().matches(Regex("\\d+"))) {
                            tmp += stack.removeLast().toInt()
                        }

                        if (stack.isEmpty() || stack.last() != "(") {
                            println(0)
                            return@with
                        }
                        stack.removeLast()
                        if (tmp == 0) stack.add("2") else stack.add((tmp * 2).toString())
                        tmp = 0
                    }

                    else -> {
                        println(0)
                        return@with
                    }
                }
            }
            if (stack.isEmpty()) println(0)
            println(stack.sumOf { it.toInt() })
        } catch (e: Exception) {
            println(0)
        }
    }
}

fun main() {
    Solution2504().solution()
}
