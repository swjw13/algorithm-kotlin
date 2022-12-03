package week10._10026_적록색약._1041_주사위

import java.util.Collections.max
import java.util.Collections.min

fun main() = with(System.`in`.bufferedReader()) {

    var ans = 0L

    val n = readln().toLong()
    val lst = readln().trim().split(" ").map { it.toLong() }

    if (n == 1L){
        println(lst.sum() - max(lst))
        return@with
    }

    val minForOneSide = min(lst)
    ans += minForOneSide * ((n - 2) * (n - 2) + 4 * (n - 2) * (n - 1))

    val twoSidePair = lst.double()
    val twoSideUnable = listOf(Pair(0, 5), Pair(2, 3), Pair(1, 4))
    var minForTwoSide = Long.MAX_VALUE
    twoSidePair.forEach {
        if (it !in twoSideUnable) {
            minForTwoSide = minOf(minForTwoSide, lst[it.first] + lst[it.second])
        }
    }
    ans += minForTwoSide * ((n - 1) * 4 + (n - 2) * 4)

    val threeSidePair = lst.triple()
    var minForThreeSide = Long.MAX_VALUE
    threeSidePair.forEach{
        if (
            Pair(it.first, it.second) !in twoSideUnable
            && Pair(it.first, it.third) !in twoSideUnable
            && Pair(it.second, it.third) !in twoSideUnable
        ){
            minForThreeSide = minOf(minForThreeSide, lst[it.first] + lst[it.second] + lst[it.third])
        }
    }
    ans += minForThreeSide * 4

    println(ans)
}

fun <T> List<T>.double(): List<Pair<Int, Int>> {
    val res = mutableListOf<Pair<Int, Int>>()
    for (i in this.indices) {
        for (j in i + 1 until this.size) {
            res.add(Pair(i, j))
        }
    }
    return res.toList()
}

fun <T> List<T>.triple(): List<Triple<Int, Int, Int>>{
    val res = mutableListOf<Triple<Int, Int, Int>>()
    for (i in this.indices){
        for (j in i + 1 until this.size){
            for (k in j + 1 until this.size){
                res.add(Triple(i, j, k))
            }
        }
    }
    return res.toList()
}