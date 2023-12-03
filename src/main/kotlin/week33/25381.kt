package week33

/**
 * 목표를 B를 가장 많이 없애는 것
 */
private object Solution25381 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val s = readln().trim()
        val length = s.length


        var cnt = 0
        val visited = MutableList(length) { false }

        val queue = ArrayDeque<Int>()
        for (idx in 0 until length){
            if (s[idx] == 'B') queue.add(idx)
            else if (s[idx] == 'C' && queue.isNotEmpty()) {
                val curIdx = queue.removeFirst()
                visited[curIdx] = true
                cnt += 1
            }
        }

        var aCount = 0
        for (idx in 0 until length){
            if (s[idx] == 'A') aCount += 1
            else if (s[idx] == 'B' && visited[idx].not() && aCount > 0){
                aCount -= 1
                cnt += 1
            }
        }

        println(cnt)
    }
}

fun main() {
    Solution25381.solution()
}

// BAABBC