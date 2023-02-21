package week17._2179_비슷한단어

/**
 * 정답 후보군을 Set으로 묶고 그 중에서 idx가 가장 앞인 값을 추출해 보자.
 */
private class Solution2179 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        val words = mutableListOf<String>()
        for (i in 0 until n) words.add(readln().trim())

        val sortedWords = mutableListOf<Pair<Int, String>>()
        val cache = mutableSetOf<String>()
        words.forEachIndexed { index, s ->
            if (s !in cache) {
                cache.add(s)
                sortedWords.add(Pair(index, s))
            }
        }
        sortedWords.sortBy { it.second }

        var prefix = ""
        var idxs = mutableListOf<Int>()
        var minIdx = -1
        for (i in 0 until sortedWords.size - 1) {
            if (sortedWords[i].second == sortedWords[i + 1].second) continue

            val curPrefix = getPrefix(sortedWords[i].second, sortedWords[i + 1].second)
            if (curPrefix.length > prefix.length) {
                prefix = curPrefix
                idxs = mutableListOf(sortedWords[i].first, sortedWords[i + 1].first)
                minIdx = minOf(sortedWords[i].first, sortedWords[i + 1].first)
            } else if (curPrefix.length == prefix.length) {
                if (curPrefix == prefix) {
                    idxs.addAll(listOf(sortedWords[i].first, sortedWords[i + 1].first))
                    minIdx = minOf(minIdx, sortedWords[i].first, sortedWords[i + 1].first)
                } else {
                    val curMin = minOf(sortedWords[i].first, sortedWords[i + 1].first)
                    if (curMin < minIdx) {
                        minIdx = curMin
                        prefix = curPrefix
                        idxs = mutableListOf(sortedWords[i].first, sortedWords[i + 1].first)
                    }
                }
            }
        }
        idxs = idxs.distinct().sorted().toMutableList()
        println(words[idxs[0]])
        println(words[idxs[1]])
    }

    private fun getPrefix(str1: String, str2: String): String {
        var idx = 0
        while (idx < str1.length && idx < str2.length && str1[idx] == str2[idx]) idx += 1
        return str1.substring(0, idx)
    }
}

fun main() {
    Solution2179().solution()
}
