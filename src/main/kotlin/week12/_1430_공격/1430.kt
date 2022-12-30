package week12._1430_공격

import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    var res = 0.0

    val (n, r, d, x, y) = readln().split(" ").map { it.toInt() }
    val tops = mutableListOf<Pair<Int, Int>>()
    val closePoint = mutableMapOf<Int, MutableSet<Int>>().apply {
        for (idx in 0 until n) {
            this[idx] = mutableSetOf()
        }
    }

    for (i in 0 until n) {
        val (curX, curY) = readln().split(" ").map { it.toInt() }
        tops.add(Pair(curX, curY))
    }

    for (idx in tops.indices) {
        for (pairIdx in tops.indices) {
            if (idx != pairIdx && distance(tops[idx], tops[pairIdx]) <= r) {
                closePoint[idx]?.add(pairIdx)
            }
        }
    }

    for (i in tops.indices) {
        val queue = ArrayDeque<Pair<Int, Double>>().apply {
            this.add(Pair(i, d.toDouble()))
        }
        val visited = MutableList(n) { false }.apply {
            this[i] = true
        }

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (distance(tops[current.first], Pair(x, y)) <= r) {
                res += current.second
                break
            }

            for (idx in closePoint[current.first]!!){
                if (visited[idx].not()){
                    visited[idx] = true
                    queue.add(Pair(idx, current.second / 2))
                }
            }
        }
    }
    println(res)
}

fun distance(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Double {
    val dist: Double = sqrt((p1.first - p2.first).toDouble().pow(2.0) + (p1.second - p2.second).toDouble().pow(2.0))
    return round(dist * 100) / 100
}