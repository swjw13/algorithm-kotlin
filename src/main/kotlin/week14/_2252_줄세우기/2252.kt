package week14._2252_줄세우기

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val treeIn = Array(n + 1){ mutableSetOf<Int>() }
    val treeOut = Array(n + 1){ mutableSetOf<Int>() }
    val ans = mutableListOf<Int>()
    val queue = ArrayDeque<Int>()

    for (i in 0 until m){
        val(bigger, smaller) = readln().split(" ").map { it.toInt() }
        treeIn[smaller].add(bigger)
        treeOut[bigger].add(smaller)
    }

    for (i in 1 .. n) {
        if (treeIn[i].size == 0) queue.add(i)
    }

    while (queue.isNotEmpty()){
        val curStudent = queue.removeFirst()
        ans.add(curStudent)

        treeOut[curStudent].forEach {
            treeIn[it].remove(curStudent)
            if (treeIn[it].size == 0 && it !in ans) queue.add(it)
        }
    }

    for (i in 1 .. n){
        if (i !in ans) ans.add(i)
    }

    println(ans.joinToString(" "))
}