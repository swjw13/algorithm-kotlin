package week17._2251_물통

/**
 * pour 함수를 좀 더 간단하게 해 보자
 */

data class Water(
    var a: Int = -1,
    var b: Int = -1,
    var c: Int = -1
)

enum class Glass {
    A, B, C
}

object Solution2251 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val res = mutableSetOf<Int>()
        val (a, b, c) = readln().split(" ").map { it.toInt() }
        val visited = List(201) { List(201) { MutableList(201) { false } } }

        val queue = ArrayDeque<Water>().apply {
            add(Water(0, 0, c))
            visited[0][0][c] = true
        }

        fun waterVisited(water: Water): Boolean = visited[water.a][water.b][water.c]

        fun pour(from: Glass, to: Glass, elseGlass: Glass, water: Water): Water? {
            val beforeFromRemain = when (from) {
                Glass.A -> water.a
                Glass.B -> water.b
                Glass.C -> water.c
            }
            val beforeToRemain = when (to) {
                Glass.A -> a - water.a
                Glass.B -> b - water.b
                Glass.C -> c - water.c
            }

            val afterFromRemain = when {
                beforeToRemain >= beforeFromRemain -> 0
                else -> beforeFromRemain - beforeToRemain
            }
            val afterToRemain = when {
                beforeToRemain >= beforeFromRemain -> beforeToRemain - beforeFromRemain
                else -> 0
            }

            val newWater = Water().apply {
                when (from) {
                    Glass.A -> this.a = afterFromRemain
                    Glass.B -> this.b = afterFromRemain
                    Glass.C -> this.c = afterFromRemain
                }

                when (to) {
                    Glass.A -> this.a = a - afterToRemain
                    Glass.B -> this.b = b - afterToRemain
                    Glass.C -> this.c = c - afterToRemain
                }

                when (elseGlass) {
                    Glass.A -> this.a = water.a
                    Glass.B -> this.b = water.b
                    Glass.C -> this.c = water.c
                }
            }

            return if (waterVisited(newWater).not()) {
                visited[newWater.a][newWater.b][newWater.c] = true
                newWater
            } else null
        }

        while (queue.isNotEmpty()) {
            val curWater = queue.removeFirst()

            if (curWater.a == 0) {
                res.add(curWater.c)
            }

            if (curWater.a != 0) {
                pour(Glass.A, Glass.B, Glass.C, curWater)?.let { queue.add(it) }
                pour(Glass.A, Glass.C, Glass.B, curWater)?.let { queue.add(it) }
            }

            if (curWater.b != 0) {
                pour(Glass.B, Glass.A, Glass.C, curWater)?.let { queue.add(it) }
                pour(Glass.B, Glass.C, Glass.A, curWater)?.let { queue.add(it) }
            }

            if (curWater.c != 0) {
                pour(Glass.C, Glass.A, Glass.B, curWater)?.let { queue.add(it) }
                pour(Glass.C, Glass.B, Glass.A, curWater)?.let { queue.add(it) }
            }
        }

        println(res.sorted().joinToString(" "))
    }
}

fun main() {
    Solution2251.solution()
}