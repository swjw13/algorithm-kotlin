package week28

/**
 * 가장 먼저 생각한 방법은 Stack
 * 풀이 방법을 보고 그리디로 해결
 */
private class Solution24524 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val positionMap = mutableMapOf<Char, ArrayDeque<Int>>()
        val visited = MutableList(26) { false }

        val s = readln().trim()
        val t = readln().trim()

        t.forEach {
            positionMap[it] = ArrayDeque()
            visited[it.code - 'a'.code] = true
        }

        s.forEachIndexed { index, c ->
            if (visited[c.code - 'a'.code]) {
                positionMap[c]?.add(index)
            }
        }

        var cnt = 0
        var idx: Int
        loop@ while (true) {
            idx = 0
            for (c in t) {
                val curMap = positionMap[c]!!
                while (curMap.isNotEmpty() && curMap.first() < idx) {
                    positionMap[c]!!.removeFirst()
                }

                if (curMap.isEmpty()) break@loop
                else idx = curMap.removeFirst()
            }
            cnt += 1
        }

        println(cnt)

    }
}

fun main() {
    Solution24524().solution()
}