package week26

private class Solution16472 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        val word = readln().trim()

        var frontIdx = 0
        var endIdx = 0

        val countMap = mutableMapOf<Char, Int>().apply {
            "abcdefghijklmnopqrstuvwxyz".forEach {
                put(it, 0)
            }
            put(word[0], 1)
        }

        var result = -1
        var wordCnt = 1
        var totalLength = 1

        while (endIdx < word.length) {
            if (wordCnt <= n) {
                result = maxOf(result, totalLength)

                endIdx += 1
                if (endIdx >= word.length) continue

                if (countMap[word[endIdx]] == 0) wordCnt += 1
                totalLength += 1
                countMap[word[endIdx]] = countMap[word[endIdx]]!! + 1

            } else {
                countMap[word[frontIdx]] = countMap[word[frontIdx]]!! - 1
                if (countMap[word[frontIdx]] == 0) wordCnt -= 1
                totalLength -= 1
                frontIdx += 1
            }
        }

        println(result)
    }
}

fun main() {
    Solution16472().solution()
}