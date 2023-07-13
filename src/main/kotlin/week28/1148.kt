package week28

private class Solution1148 {

    data class WordBox(
        var cnt: Int,
        val charList: MutableList<Char>
    )

    fun solution() = with(System.`in`.bufferedReader()) {
        val words = mutableListOf<MutableList<Int>>()
        while (true) {
            val word = readln().trim()
            if (word == "-") break
            val wordList = MutableList(26) { 0 }
            for (c in word) {
                wordList[c.code - 'A'.code] += 1
            }
            words.add(wordList)
        }

        val minWordList = WordBox(200001, mutableListOf())
        val maxWordList = WordBox(0, mutableListOf())

        while (true) {
            val mp = readln().trim()
            if (mp == "#") break

            val mpWordList = MutableList(26) { 0 }
            for (c in mp) {
                mpWordList[c.code - 'A'.code] += 1
            }

            minWordList.cnt = 200001
            minWordList.charList.clear()

            maxWordList.cnt = 0
            maxWordList.charList.clear()

            for (token in mp.toSortedSet()) {
                var a = 0
                loop@ for (wordList in words) {
                    if (wordList[token.code - 'A'.code] == 0) continue

                    for (idx in 0 until 26) {
                        if (wordList[idx] > mpWordList[idx]) continue@loop
                    }

                    a += 1
                }

                if (a < minWordList.cnt) {
                    minWordList.cnt = a
                    minWordList.charList.clear()
                    minWordList.charList.add(token)
                } else if (a == minWordList.cnt) minWordList.charList.add(token)

                if (a > maxWordList.cnt) {
                    maxWordList.cnt = a
                    maxWordList.charList.clear()
                    maxWordList.charList.add(token)
                } else if (a == maxWordList.cnt) maxWordList.charList.add(token)
            }

            println(
                "${
                    minWordList.charList.sorted().joinToString("")
                } ${minWordList.cnt} ${maxWordList.charList.sorted().joinToString("")} ${maxWordList.cnt}"
            )
        }
    }
}

fun main() {
    Solution1148().solution()
}