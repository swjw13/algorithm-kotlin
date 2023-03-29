package week23

private class Solution21278 {

    data class HeapElement(
        val point: Int,
        val dist: Int
    )

    private fun floyd(line: List<List<Int>>, n: Int): List<List<Int>> {
        val distance = List(n + 1) { MutableList(n + 1) { 1000 } }
        for (i in 1..n) {
            distance[i][i] = 0
        }
        for (i in 0..n) {
            distance[i][0] = 0
            distance[0][i] = 0
        }

        for (row in 1 .. n){
            for (conn in line[row]){
                distance[row][conn] = 1
            }
        }

        for (k in 1..n) {
            for (i in 1..n) {
                for (j in 1..n) {
                    distance[i][j] = minOf(distance[i][j], distance[i][k] + distance[k][j])
                }
            }
        }

        return distance
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val connectedLine = MutableList(n + 1) {
            mutableListOf<Int>()
        }
        repeat(m) {
            val (start, end) = readln().split(" ").map { it.toInt() }
            connectedLine[start].add(end)
            connectedLine[end].add(start)
        }

        val distList = floyd(connectedLine, n)
        val res = mutableListOf(-1, -1, Int.MAX_VALUE)

        for (i in 1..n) {
            for (j in i + 1..n) {
                var totalDist = 0
                for (points in 1 .. n){
                    totalDist += minOf(distList[i][points], distList[j][points]) * 2
                }
                if (totalDist < res[2]) {
                    res[0] = i
                    res[1] = j
                    res[2] = totalDist
                }
            }
        }
        println(res.joinToString(" "))
    }
}

fun main() {
    Solution21278().solution()
}