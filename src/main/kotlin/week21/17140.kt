package week21

private class Solution17140 {

    fun sorting(list: List<Int>): List<Int> {
        val res = mutableListOf<Int>()
        list.filter { it != 0 }
            .groupBy { it }
            .entries
            .sortedWith(compareBy({ it.value.size }, { it.key }))
            .forEach {
                res.add(it.key)
                res.add(it.value.size)
            }
        return res
    }

    fun actionR(lst: List<List<Int>>): List<List<Int>> {
        val maxLength: Int
        val res = lst.map { sorting(it).toMutableList() }

        maxLength = res.maxOf { it.size }
        for (idx in res.indices) {
            while (res[idx].size != maxLength) res[idx].add(0)
        }
        return res
    }

    fun actionC(lst: List<List<Int>>, colSize: Int): List<List<Int>> {
        val res = mutableListOf<MutableList<Int>>()
        var maxLength = 0

        for (col in 0 until colSize) {
            val curLst = mutableListOf<Int>()
            for (row in lst.indices) {
                if (col < lst[row].size) curLst.add(lst[row][col])
            }

            val newLst = sorting(curLst)
            res.add(newLst.toMutableList())
            maxLength = maxOf(maxLength, newLst.size)
        }

        for (idx in res.indices) {
            while (res[idx].size != maxLength) res[idx].add(0)
        }

        val newRes = List(maxLength) { MutableList(res.size) { 0 } }
        for (row in 0 until maxLength) {
            for (col in 0 until res.size) {
                newRes[row][col] = res[col][row]
            }
        }

        return newRes
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (r, c, k) = readln().split(" ").map { it.toInt() }
        var board = List(3) { readln().split(" ").map { it.toInt() } }

        var rowSize = 3
        var colSize = 3
        var turn = 0

        while ((r - 1 >= rowSize || c - 1 >= colSize) || (board[r - 1][c - 1] != k)) {

            if (turn == 100) {
                return@with -1
            }

            if (rowSize >= colSize) {
                board = actionR(board)
                colSize = board.map { it.size }.maxOf { it }
            } else {
                board = actionC(board, colSize)
                rowSize = board.size
            }

            turn += 1
        }

        return@with turn
    }
}

fun main() {
    println(Solution17140().solution())
}