package week8._14567_선수과목

/**
 * 위상정렬
 */

data class Semester(
    val subject: Int,
    val semester: Int
)

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().trim().split(" ").map { it.toInt() }

    val outLine = mutableMapOf<Int, MutableSet<Int>>()
    val inLineCount = MutableList(n + 1) { 0 }
    val answer = MutableList(n + 1) { 0 }

    for (i in 0 until m) {
        val (first, then) = readLine().trim().split(" ").map { it.toInt() }
        if (first !in outLine.keys){
            outLine[first] = mutableSetOf()
        }
        outLine[first]?.add(then)

        inLineCount[then] += 1
    }

    val queue = ArrayDeque<Semester>()
    for (i in 1 .. n){
        if (inLineCount[i] == 0){
            queue.add(Semester(i, 1))
        }
    }

    while (queue.isNotEmpty()){
        val curSubject = queue.removeFirst()
        answer[curSubject.subject] = curSubject.semester

        for (i in outLine[curSubject.subject] ?: setOf()){
            inLineCount[i] -= 1
            if (inLineCount[i] == 0){
                queue.add(Semester(i, curSubject.semester + 1))
            }
        }
    }

    println(answer.subList(1, answer.size).joinToString(" "))
}