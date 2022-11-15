package week8._1197_최소스패닝트리

import java.util.PriorityQueue

/**
 * 크루스칼 알고리즘
 */

data class Line(
    val e1: Int,
    val e2: Int,
    val weight: Int
)

fun main() = with(System.`in`.bufferedReader()) {
    val (v, e) = readLine().trim().split(" ").map { it.toInt() }

    val heap = PriorityQueue<Line> { o1, o2 ->
        o1.weight.compareTo(o2.weight)
    }

    val parent = mutableMapOf<Int, Int>().apply {
        for (i in 1..v) {
            this[i] = i
        }
    }

    fun findParent(point: Int): Int {
        return if (parent[point] == point) point
        else {
            parent[point] = findParent(parent[point]!!)
            parent[point]!!
        }
    }

    fun union(p1: Int, p2: Int) {
        val p1Parent = findParent(p1)
        val p2Parent = findParent(p2)

        if (p1Parent < p2Parent) parent[p2Parent] = p1Parent
        else parent[p1Parent] = p2Parent
    }

    for (i in 0 until e){
        val (e1, e2, w) = readLine().trim().split(" ").map { it.toInt() }
        heap.add(Line(e1, e2, w))
    }

    var ans = 0
    while (heap.isNotEmpty()){
        val curPoint = heap.poll()
        val p1Parent = findParent(curPoint.e1)
        val p2Parent = findParent(curPoint.e2)

        if (p1Parent != p2Parent){
            ans += curPoint.weight
            union(p1Parent, p2Parent)
        }
    }

    println(ans)
}