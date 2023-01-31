package week15._2258_정육점

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val meats = mutableListOf<List<Int>>()
    for (i in 0 until n) {
        meats.add(readln().split(" ").map { it.toInt() })
    }

    if (meats.sumOf { it[0] } < m) println(-1)
    else {
        meats.sortWith { o1, o2 ->
            if (o1[1] == o2[1]) {
                o2[0].compareTo(o1[0])
            } else {
                o1[1].compareTo(o2[1])
            }
        }

        var res = Int.MAX_VALUE
        var maxPrice = -1
        var totalPrice = -1
        var weight = 0
        meats.forEach {
            weight += it[0]
            if (maxPrice == it[1]) {
                totalPrice += it[1]
            } else {
                maxPrice = it[1]
                totalPrice = it[1]
            }
            if (weight >= m) res = minOf(res, totalPrice)
        }
        println(res)
    }
}