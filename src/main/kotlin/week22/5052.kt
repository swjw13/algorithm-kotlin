package week22

import java.util.*

private class Solution5052 {

    private class Trie(
        val node: TreeMap<Char, Trie>
    ) {
        fun insert(number: String): Boolean {
            var tree = this
            for (num in number) {
                if (num in tree.node.keys) {
                    if (tree.node[num]!!.node.keys.size == 0) return false
                    else tree = tree.node[num]!!
                } else {
                    tree.node[num] = Trie(TreeMap())
                    tree = tree.node[num]!!
                }
            }
            return true
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readln().toInt()
        case@ for (i in 0 until t) {
            val n = readln().toInt()
            val numbers = mutableListOf<String>()
            repeat(n) {
                numbers.add(readln().trim())
            }

            numbers.sort()

            val trie = Trie(TreeMap())
            for (num in numbers) {
                val res = trie.insert(num)
                if (res.not()) {
                    println("NO")
                    continue@case
                }
            }
            println("YES")
        }
    }
}

fun main() {
    Solution5052().solution()
}