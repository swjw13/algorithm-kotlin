package week11._1038_감소하는수

// Char.digitToInt()
fun main() = with(System.`in`.bufferedReader()) {
    val decreaseNumbers = mutableListOf<Long>()

    fun backTracking(number: String) {
        decreaseNumbers.add(number.toLong())

        for (i in 0 until number.last().toString().toInt()) {
            backTracking("$number$i")
        }
    }

    for (i in 0 .. 9){
        backTracking(i.toString())
    }

    decreaseNumbers.sort()
    val n = readln().toInt()
    if (n >= decreaseNumbers.size) println(-1) else println(decreaseNumbers[n])
}