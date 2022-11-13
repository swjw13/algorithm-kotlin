package week7._1946_신입사원

/**
 * (char -> Int) : digitToInt()
 */

data class Score(
    val first: Int,
    val second: Int
)

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    for (turn in 0 until t) {
        val n = readLine().toInt()
        val people = mutableListOf<Score>()
        for (i in 0 until n) {
            val personScore = readLine().trim().split(" ").map { it.toInt() }
            people.add(Score(personScore[0], personScore[1]))
        }

        people.sortBy { it.first }

        var threashold = Int.MAX_VALUE
        var ans = 0

        for (secondScoreSortedByFirst in people.map { it.second }){
            if (secondScoreSortedByFirst < threashold){
                threashold = secondScoreSortedByFirst
                ans += 1
            }
        }

        println(ans)
    }
}