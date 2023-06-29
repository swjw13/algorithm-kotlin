package week27

private class Solution9935 {

    private fun endsWith(stack: ArrayDeque<Char>, t: String): Boolean {
        val length = t.length
        val stackSize = stack.size
        if (stackSize < length) return false

        for (i in 0 until length) {
            if (stack[stackSize - i - 1] != t[length - i - 1]) return false
        }
        return true
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val wordToChange = readln().trim()
        val token = readln().trim()
        val tokenLength = token.length

        val stack = ArrayDeque<Char>()
        for (i in wordToChange) {
            stack.add(i)

            while (endsWith(stack, token)) {
                for (j in 0 until tokenLength) stack.removeLast()
            }
        }

        if (stack.isEmpty()) println("FRULA") else println(stack.joinToString(""))
    }
}

fun main() {
    Solution9935().solution()
}