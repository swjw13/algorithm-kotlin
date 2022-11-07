package week7._1992_쿼드트리

fun checkDuplicate(input1: String, input2: String, input3: String, input4: String): Boolean {
    val stringSet = setOf(input1, input2, input3, input4)

    return if (stringSet.size == 1){
        when (input1) {
            "0", "1" -> true
            else -> false
        }
    } else {
        false
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val lst = mutableListOf<List<Int>>()
    for (i in 0 until n) {
        val line = readLine().trim().toList().map { it.toString().toInt() }
        lst.add(line)
    }

    fun quadTree(startRow: Int, endRow: Int, startCol: Int, endCol: Int, n: Int): String {
        if (n == 1) {
            return lst[startRow][startCol].toString()
        }

        val midRow = (startRow + endRow) / 2
        val midCol = (startCol + endCol) / 2

        val secondQuad = quadTree(startRow, midRow, startCol, midCol, n / 2)
        val firstQuad = quadTree(startRow, midRow, midCol, endCol, n / 2)
        val thirdQuad = quadTree(midRow, endRow, startCol, midCol, n / 2)
        val fourthQuad = quadTree(midRow, endRow, midCol, endCol, n / 2)

        return if (checkDuplicate(firstQuad, secondQuad, thirdQuad, fourthQuad)) {
            firstQuad
        } else "(${secondQuad}${firstQuad}${thirdQuad}${fourthQuad})"
    }

    println(quadTree(0, n, 0, n, n))
}