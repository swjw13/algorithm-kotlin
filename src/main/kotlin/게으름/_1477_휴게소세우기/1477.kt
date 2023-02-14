package 게으름._1477_휴게소세우기

fun main() = with(System.`in`.bufferedReader()) {
    var res = Int.MAX_VALUE
    val (n, m, l) = readln().split(" ").map { it.toInt() }

    val lst = if (n != 0) {
        readln().split(" ").map { it.toInt() }.sorted()
    } else {
        listOf(l)
    }
    val gaps = if (n != 0){
        mutableListOf<Int>().apply {
            add(lst.first())
            add(l - lst.last())
            for (i in 0 until n - 1) {
                add(lst[i + 1] - lst[i])
            }
        }
    } else {
        mutableListOf(l)
    }

    var start = 1
    var end = l
    while (start <= end) {
        val mid = (start + end) / 2
        var cnt = 0
        for (gap in gaps) {
            cnt += gap / mid
            if (gap % mid == 0) cnt -= 1
        }

        if (cnt > m) {
            start = mid + 1
        } else if (cnt == m) {
            res = minOf(res, mid)
            end = mid - 1
        } else {
            res = minOf(res, mid)
            end = mid - 1
        }
    }

    println(res)
}