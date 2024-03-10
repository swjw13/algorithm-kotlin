package week39

// readText()
private object Solution6581 {
    fun solution() {
        val lineConst = "--------------------------------------------------------------------------------"
        val input = generateSequence(::readLine).joinToString("\n")
        val tokens = input.split(Regex("\\s+"))

        var size = 0
        val resultSb = StringBuilder()
        val result = mutableListOf<String>()

        for (word in tokens){
            if (word == "<br>"){
                if (resultSb.isNotEmpty()){
                    result.add(resultSb.toString().trim())
                    size = 0
                    resultSb.clear()
                }
            } else if (word == "<hr>"){
                if (resultSb.isNotEmpty()){
                    result.add(resultSb.toString().trim())
                    size = 0
                    resultSb.clear()
                }

                result.add(lineConst)
            } else {
                resultSb.append(word)
                size += word.length

                if (size > 80){
                    result.add(resultSb.toString().trim())
                    size = 0
                    resultSb.clear()
                }
            }
        }
        println(result.joinToString("\n"))
    }
}

fun main(){
    Solution6581.solution()
}