package week27

private class Solution23740 {

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        val busList = mutableListOf<List<Int>>()
        val answer = mutableListOf<List<Int>>()
        for (i in 0 until n) {
            val (s, e, p) = readln().split(" ").map { it.toInt() }
            busList.add(listOf(s, e, p))
        }

        busList.sortBy { it[0] }

        var startPoint = busList[0][0]
        var endPoint = busList[0][1]
        var minPrice = busList[0][2]

        for (curBus in busList.subList(1, n)) {
            if (curBus[0] > endPoint) {
                answer.add(listOf(startPoint, endPoint, minPrice))
                startPoint = curBus[0]
                endPoint = curBus[1]
                minPrice = curBus[2]
            } else {
                endPoint = maxOf(endPoint, curBus[1])
                minPrice = minOf(minPrice, curBus[2])
            }
        }

        answer.add(listOf(startPoint, endPoint, minPrice))

        val sb = StringBuilder().append(answer.size).append('\n')
        for (i in answer) sb.append("${i[0]} ${i[1]} ${i[2]}\n")
        println(sb)
    }

}

fun main() {
    Solution23740().solution()
}