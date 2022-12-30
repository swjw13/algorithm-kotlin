package week12._1461_도서관

import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val (_, m) = readln().split(" ").map { it.toInt() }
    val lst = readln().split(" ").map { it.toInt() }.sortedWith { o1, o2 ->
        when {
            o1 * o2 <= 0 -> o2.compareTo(o1)
            else -> {
                abs(o2).compareTo(abs(o1))
            }
        }
    }.toMutableList()

    var destinationPoint = 0
    val prevDest = mutableListOf<Int>()

    while (lst.isNotEmpty()){
        destinationPoint = lst.removeFirst()
        var cnt = 1
        while (lst.isNotEmpty() && cnt < m){
            if (lst.first() * destinationPoint < 0) break
            lst.removeFirst()
            cnt += 1
        }

        prevDest.add(abs(destinationPoint))
    }

    println(prevDest.sum() * 2 - prevDest.maxOf { it })
}