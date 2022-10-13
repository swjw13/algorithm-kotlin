package week3._14719_빗물

fun main() {
    with(System.`in`.bufferedReader()) {
        var res = 0
        val (h, w) = readLine().split(" ").map { it.toInt() }
        val lst = readLine().split(" ").map { it.toInt() }

        for (height in 1..h) {
            var idx = 0
            var cnt = 0
            var inHole = false
            while (idx < w - 1) {
                when {
                    lst[idx] >= height && lst[idx + 1] >= height -> {}
                    lst[idx] >= height && lst[idx + 1] < height -> {
                        inHole = true
                    }
                    lst[idx] < height && lst[idx + 1] >= height -> {
                        if (inHole) {
                            cnt += 1
                            res += cnt
                            cnt = 0
                        }
                        inHole = false
                    }
                    else -> {
                        if (inHole) {
                            cnt += 1
                        }
                    }
                }
                idx += 1
            }
        }

        println(res)
    }
}