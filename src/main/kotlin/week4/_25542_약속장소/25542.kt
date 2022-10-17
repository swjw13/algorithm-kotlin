package week4._25542_약속장소

fun main() {
    val wholeWords = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    fun findAvailableDest(word: String): MutableSet<String> {
        val res = mutableSetOf<String>()

        val wordList: MutableList<Char> = word.toList().toMutableList()

        for (i in wordList.indices) {
            val curWord = wordList[i]

            wholeWords.forEach {
                wordList[i] = it
                res.add(wordList.joinToString(""))
            }
            wordList[i] = curWord
        }

        return res
    }

    with(System.`in`.bufferedReader()) {

        val (n, l) = readLine().split(" ").map { it.toInt() }
        val firstWord = readLine()
        var answerSet = findAvailableDest(firstWord)

        for (i in 1 until n) {
            val word = readLine()
            val wordSet = findAvailableDest(word)
            answerSet = answerSet.intersect(wordSet).toMutableSet()

            if (answerSet.size == 0) break
        }

        if (answerSet.size == 0) {
            println("CALL FRIEND")
        } else {
            println(answerSet.random())
        }
    }
}