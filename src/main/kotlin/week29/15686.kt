package week29

import kotlin.math.abs

private class Solution15686 {

    data class Point(
        val row: Int,
        val col: Int
    )

    val pickedNum = mutableListOf<Int>()

    private fun combination(cnt: Int, maxSize: Int, depth: Int, startWith: Int, action: () -> Unit) {
        if (cnt == depth) {
            action()
            return
        }

        for (i in startWith until maxSize) {
            pickedNum.add(i)
            combination(cnt + 1, maxSize, depth, i + 1, action)
            pickedNum.removeLast()
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (r, c) = readln().split(" ").map { it.toInt() }
        val houses = mutableListOf<Point>()
        val chicken = mutableListOf<Point>()

        for (i in 0 until r) {
            val lst = readln().split(" ").map { it.toInt() }
            for (j in 0 until r) {
                if (lst[j] == 1) houses.add(Point(i, j))
                else if (lst[j] == 2) chicken.add(Point(i, j))
            }
        }

        var answer = Int.MAX_VALUE
        combination(0, chicken.size, c, 0) {
            var tmp = 0
            for (curHouse in houses) {
                var curChickenDistance = Int.MAX_VALUE
                for (idx in pickedNum) {
                    val curChicken = chicken[idx]
                    curChickenDistance = minOf(
                        curChickenDistance,
                        abs(curHouse.row - curChicken.row) + abs(curHouse.col - curChicken.col)
                    )
                }
                tmp += curChickenDistance
            }

            answer = minOf(answer, tmp)
        }

        println(answer)
    }
}

fun main() {
    Solution15686().solution()
}