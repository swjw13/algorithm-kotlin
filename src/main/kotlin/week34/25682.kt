package week34

private object Solution25682 {

    data class DpElement(
        var cntForLeftRightWhite: Int,
        var cntForLeftRightBlack: Int
    ) {
        override fun toString(): String {
            return "($cntForLeftRightWhite, $cntForLeftRightBlack)"
        }

        fun getElementByIdx(idx: Int): Int {
            return if (idx == 0) cntForLeftRightWhite
            else cntForLeftRightBlack
        }

        fun setElementByIdx(idx: Int, value: Int){
            if (idx == 0) cntForLeftRightWhite = value
            else cntForLeftRightBlack = value
        }
    }

    fun changeColor(color: Char): Char {
        return if (color == 'B') 'W'
        else 'B'
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, k) = readln().split(" ").map { it.toInt() }
        val board = mutableListOf<String>()
        for (i in 0 until n) {
            board.add(readln().trim())
        }

        val dp = MutableList(n) { MutableList(m) { DpElement(0, 0) } }
        var word: Char
        var cnt: Int

        for (row in 0 until n) {
            word = 'B'
            cnt = 0
            for (col in 0 until k) {
                if (board[row][col] != word) cnt += 1
                word = changeColor(word)
            }
            dp[row][k - 1].cntForLeftRightBlack = cnt

            word = 'W'
            cnt = 0
            for (col in 0 until k) {
                if (board[row][col] != word) cnt += 1
                word = changeColor(word)
            }
            dp[row][k - 1].cntForLeftRightWhite = cnt
        }

//        for (col in k until m){
//            for (row in 0 until n){
//                cnt = dp[row][col - 1].cntForLeftRightWhite
//                if (board[row][col])
//            }
//        }


        var result = Int.MAX_VALUE
        var idx: Int
        var startIdx: Int
        var total: Int
        for (col in k - 1 until m) {
            total = 0
            idx = 0
            startIdx = idx
            for (row in 0 until k){
                total += dp[row][col].getElementByIdx(idx)
                idx = idx xor 1
            }

            result = minOf(result, total)
            for (row in k until n){
                result -= dp[row - k][col].getElementByIdx(startIdx)
                startIdx = startIdx xor 1

                result += dp[row][col].getElementByIdx(idx)
                idx = idx xor 1
            }

            total = 0
            idx = 1
            startIdx = idx
            for (row in 0 until k){
                total += dp[row][col].getElementByIdx(idx)
                idx = idx xor 1
            }

            result = minOf(result, total)
            for (row in k until n){
                result -= dp[row - k][col].getElementByIdx(startIdx)
                startIdx = startIdx xor 1

                result += dp[row][col].getElementByIdx(idx)
                idx = idx xor 1
            }
        }

        println(result)
    }
}

fun main() {
    Solution25682.solution()
}