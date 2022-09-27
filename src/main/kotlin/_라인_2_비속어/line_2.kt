package _라인_2_비속어

class Solution {
    fun solution(k: Int, dic: List<String>, chat: String): String {
        val res = mutableListOf<String>()
        val wordList = chat.split(" ")
        for (word in wordList) {
            var regexString = ""
            for (char in word) {
                if (char == '.') {
                    regexString += "[a-z]{1,$k}"
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