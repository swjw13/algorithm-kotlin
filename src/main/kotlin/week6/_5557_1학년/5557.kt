package week6._5557_1학년

fun add(a: Int, b: Int): Int = a + b
fun sub(a: Int, b: Int): Int = a - b

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val numbers = readLine().split(" ").map { it.toInt() }.toMutableList()
    val maths = listOf<(Int, Int) -> Int>(::add, ::sub)

    val dp = List(21) { MutableList(n) { 0L } }
    dp[numbers.first()][0] = 1L

    for (col in 0 until n - 2){
        for (row in 0 .. 20){
            if (dp[row][col] != 0L){
                for (op in maths){
                    val newNumber = op(row, numbers[col + 1])
                    if (newNumber in 0 .. 20){
                        dp[newNumber][col + 1] += dp[row][col]
                    }
                }
            }
        }
    }

    println(dp[numbers.last()][n - 2])
}