package week42

private object Solution11509 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val lst = readLine().split(" ").map { it.toInt() }

        val cntList = MutableList(lst.maxOf { it } + 1) { 0 }
        for (i in lst) {
            if (cntList[i] != 0) {
                cntList[i] -= 1
            }
            cntList[i - 1] += 1
        }

        println(cntList.sum())
    }
}

fun main() {
    Solution11509.solution()
}