package week4._2559_수열

/**
 * StreamTokenizer 찾아보기
 */

fun main() {
    with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val lst = readLine().split(" ").map { it.toInt() }

        var sum = lst.subList(0, k).sum()
        var ans = sum
        for (endIdx in k until n) {
            sum += (lst[endIdx] - lst[endIdx - k])
            ans = maxOf(ans, sum)
        }

        println(ans)
    }
}