package week1._라인_2_비속어

class Solution {
    fun solution(k: Int, dic: List<String>, chat: String): String {

        /**
         * Queue, Stack 을 ArrayDeque 로 풀어보기
         */

        val res = mutableListOf<String>()
        val wordList = chat.split(" ")
        for (word in wordList) {
            var regexString = ""
            for (char in word) {
                if (char == '.') {
                    regexString += "[\\w]{1,$k}"
                } else {
                    regexString += char
                }
            }

            val regex = regexString.toRegex()

            var isFound = false
            for (dicWord in dic) {
                if (regex.matches(dicWord)) {
                    isFound = true
                    var changedWord = ""
                    for (i in word.indices) changedWord += "#"
                    res.add(changedWord)
                    break
                }
            }
            if (!isFound) {
                res.add(word)
            }
        }

        return res.joinToString(" ")
    }
}

fun main(){
    val reg = Regex(".")
    println(reg.matches(" "))
}