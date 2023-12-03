package week35

private class Solution2212 {

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        val k = readln().toInt()
        val sensors = readln().split(" ").map { it.toInt() }.toSet().toList().sorted()

        if (k >= n) println(0)
        else {
            val intervalList = mutableListOf<Int>()
            for (i in 1 until sensors.size){
                intervalList.add(sensors[i] - sensors[i - 1])
            }
            intervalList.sortByDescending { it }
            println(intervalList.subList(k - 1, intervalList.size).sum())
        }
    }
}

fun main() {
    Solution2212().solution()
}