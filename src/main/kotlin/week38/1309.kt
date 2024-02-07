package week38

private object Solution1309 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()

        var zero = 1L
        var one = 1L
        var total: Long = zero + 2 * one

        for (i in 1 until n){
            val newZero = zero + 2 * one
            val newOne = zero + one

            zero = newZero % 9901
            one = newOne % 9901
            total = (zero + 2 * one) % 9901
        }

        println(total % 9901)
    }
}

fun main() {
    Solution1309.solution()
}