package week41

private object Solution18869 {

    fun solution() = with(System.`in`.bufferedReader()) {
        val (m, n) = readLine().split(" ").map { it.toInt() }
        val orbits = mutableListOf<List<Int>>()

        for (i in 0 until m) {
            val curOrbit = readLine().split(" ").distinct()
                .withIndex()
                .sortedBy { it.value }
                .map { it.index }

            orbits.add(curOrbit)
        }

        var cnt = 0
        for (i in 0 until m - 1) {
            for (j in i + 1 until m) {
                if (orbits[i].equal(orbits[j])) {
                    cnt += 1
                }
            }
        }

        println(cnt)
    }
}

fun List<Int>.equal(other: List<Int>): Boolean {
    if (size != other.size) return false
    for (i in indices) {
        if (get(i) != other[i]) return false
    }

    return true
}

fun main() {
    Solution18869.solution()
}
