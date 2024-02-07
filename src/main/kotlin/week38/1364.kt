package week38

private object Solution1364 {
    fun solution() = with(System.`in`.bufferedReader()){
        val n = readln().toInt()

        var turn = 1L
        var addOn = 0L
        var result = 1L

        while (turn <= n){
            when(turn % 6) {
                0L -> {
                    result += addOn + 1
                }

                1L -> {
                    result += addOn
                    addOn += 1
                }
                
                else -> {
                    result += addOn
                }
            }

            turn += 1
        }

        println(result)
    }
}

fun main(){
    Solution1364.solution()
}