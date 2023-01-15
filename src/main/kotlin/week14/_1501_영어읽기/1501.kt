package week14._1501_영어읽기

fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    val myDictionary = mutableMapOf<String, MutableMap<String, Int>>()

    for (i in 0 until n) {
        val curWord = readln()
        val dictKey: String
        val wordKey: String

        if (curWord.isEmpty()) continue
        else if (curWord.length == 1) {
            dictKey = "ONE"
            wordKey = curWord
        } else {
            dictKey = "${curWord.first()}${curWord.last()}"
            wordKey = curWord.substring(1, curWord.length - 1).toList().sorted().joinToString("")
        }

        if (dictKey !in myDictionary.keys) myDictionary[dictKey] = mutableMapOf()
        if (wordKey !in myDictionary[dictKey]!!.keys) myDictionary[dictKey]!![wordKey] = 0

        myDictionary[dictKey]!![wordKey] = myDictionary[dictKey]!![wordKey]!! + 1
    }

    val m = readln().toInt()
    for (i in 0 until m) {
        val word = readln().trim().split(" ")
        var res = 1

        for (wordToFind in word){
            val dictKey: String
            val wordKey: String

            if (wordToFind.isEmpty()) {
                res = 0
                continue
            }
            else if (wordToFind.length == 1){
                dictKey = "ONE"
                wordKey = wordToFind
            } else {
                dictKey = "${wordToFind.first()}${wordToFind.last()}"
                wordKey = wordToFind.substring(1, wordToFind.length - 1).toList().sorted().joinToString("")
            }

            res *= if (dictKey !in myDictionary.keys) 0
            else if (wordKey !in myDictionary[dictKey]!!.keys) 0
            else myDictionary[dictKey]?.get(wordKey)!!
        }
        println(res)
    }
}