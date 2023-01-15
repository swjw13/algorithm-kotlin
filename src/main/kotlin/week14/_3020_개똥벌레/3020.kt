package week14._3020_개똥벌레

fun main() = with(System.`in`.bufferedReader()) {
    val (n, h) = readln().split(" ").map { it.toInt() }
    val topEntry = IntArray(h + 1) { 0 }
    val bottomEntry = IntArray(h + 1) { 0 }
    for (i in 0 until n) {
        val height = readln().toInt()

        if (i % 2 == 0) bottomEntry[height] += 1
        else topEntry[height] += 1
    }

    for (i in h - 1 downTo 1) {
        bottomEntry[i] += bottomEntry[i + 1]
        topEntry[i] += topEntry[i + 1]
    }

    var cnt = 0
    var rocks = Int.MAX_VALUE

    for (height in 1..h) {
        val tmp = bottomEntry[height] + topEntry[h - height + 1]
        if (tmp < rocks) {
            rocks = tmp
            cnt = 1
        } else if (tmp == rocks) {
            cnt += 1
        }
    }
    println("$rocks $cnt")
}