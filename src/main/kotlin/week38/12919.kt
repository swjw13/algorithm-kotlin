package week38

private object Solution12919 {
    private lateinit var first: String
    fun solution() = with(System.`in`.bufferedReader()){
        first = readln().trim()
        val second = readln().trim()

        if (gaming(second)) println(1)
        else println(0)
    }

    fun gaming(word: String): Boolean {
        if (word.length == first.length) {
            return word == first
        }

        if (word.last() == 'A'){
            val firstResult = gaming(word.dropLast(1))
            if (firstResult) return firstResult
        }
        if (word.first() == 'B'){
            val secondResult = gaming(word.reversed().dropLast(1))
            if (secondResult) return secondResult
        }

        return false

    }
}

fun main(){
    Solution12919.solution()
}