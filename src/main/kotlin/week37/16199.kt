package week37

private object Solution16199 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (myYear, myMonth, myDate) = readln().split(" ").map { it.toInt() }
        val (targetYear, targetMonth, targetDate) = readln().split(" ").map { it.toInt() }

        if ((myMonth < targetMonth) || (myMonth == targetMonth && myDate <= targetDate)) {
            println(targetYear - myYear)
        } else {
            println(targetYear - myYear - 1)
        }

        println(targetYear - myYear + 1)
        println(targetYear - myYear)
    }
}

fun main() {
    Solution16199.solution()
}