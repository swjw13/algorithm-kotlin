package week37

private object Solution6137 {
    fun solution() = with(System.`in`.bufferedReader()){
        val n = readln().toInt()
        val initStringBuilder = StringBuilder()

        for (i in 0 until n){
            val c = readln().trim()
            initStringBuilder.append(c)
        }

        val initWord = initStringBuilder.toString()
        var start = 0
        var end = n - 1

        val resultStringBuilder = StringBuilder()
        while (start <= end){
            if (initWord[start] < initWord[end]) {
                resultStringBuilder.append(initWord[start])
                start += 1
            } else if (initWord[start] > initWord[end]){
                resultStringBuilder.append(initWord[end])
                end -= 1
            } else {
                findNextIdx(start, end, initWord).let {
                    if (it == start) {
                        resultStringBuilder.append(initWord[start])
                        start += 1
                    } else {
                        resultStringBuilder.append(initWord[end])
                        end -= 1
                    }
                }
            }
        }

        val result = resultStringBuilder.toString()
        result.chunked(80).forEach {
            println(it)
        }
    }

    fun findNextIdx(front: Int, back: Int, word: String): Int? {
        var startIdx = front
        var endIdx = back

        while (startIdx < endIdx){
            if (word[startIdx] < word[endIdx]) return front
            else if (word[startIdx] > word[endIdx]) return back
            else {
                startIdx += 1
                endIdx -= 1
            }
        }

        return null
    }
}

fun main(){
    Solution6137.solution()
}