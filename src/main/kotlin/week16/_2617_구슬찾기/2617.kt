package week16._2617_구슬찾기

const val INF = 100000
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }

    val biggerDst = List(n + 1) { MutableList(n + 1) { INF } }.apply {
        for (i in 1..n) this[i][i] = 0
    }
    val smallerDst = List(n + 1) { MutableList(n + 1) { INF } }.apply {
        for (i in 1..n) this[i][i] = 0
    }

    for (i in 0 until m) {
        val (b, s) = readln().split(" ").map { it.toInt() }
        biggerDst[b][s] = 1
        smallerDst[s][b] = 1
    }

    for (k in 1..n) {
        for (i in 1..n) {
            for (j in 1..n) {
                biggerDst[i][j] = minOf(biggerDst[i][j], biggerDst[i][k] + biggerDst[k][j])
                smallerDst[i][j] = minOf(smallerDst[i][j], smallerDst[i][k] + smallerDst[k][j])
            }
        }
    }

    val res = mutableSetOf<Int>()
    var cnt: Int
    for (i in 1..n) {
        cnt = 0
        for (j in 1..n) {
            if (biggerDst[i][j] != INF && biggerDst[i][j] != 0) cnt += 1
        }
        if (cnt > n / 2) res.add(i)

        cnt = 0
        for (j in 1..n) {
            if (smallerDst[i][j] != INF && smallerDst[i][j] != 0) cnt += 1
        }
        if (cnt > n / 2) res.add(i)
    }

    println(res.size)
}