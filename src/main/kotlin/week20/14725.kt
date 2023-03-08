package week20

import java.util.TreeMap

private class Solution14725 {

    private class Trie(
        val node: TreeMap<String, Trie>
    ) {
        fun insert(elements: List<String>) {
            var tree = this
            for (element in elements){
                if (element in tree.node.keys){
                    tree = tree.node[element]!!
                } else {
                    tree.node[element] = Trie(TreeMap())
                    tree = tree.node[element]!!
                }
            }
        }
    }

    private fun show(prefix: String, trie: Trie){
        if (trie.node.keys.size != 0) {
            for (key in trie.node.keys) {
                println("$prefix$key")
                show("$prefix--", trie.node[key]!!)
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        val trie = Trie(TreeMap())

        for (i in 0 until n){
            val line = readln().split(" ")
            trie.insert(line.subList(1, 1 + line[0].toInt()))
        }

        show("", trie)
    }
}

fun main() {
    Solution14725().solution()
}