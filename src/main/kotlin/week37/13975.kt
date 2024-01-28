package week37

import java.util.PriorityQueue

private object Solution13975 {
    fun solution() = with(System.`in`.bufferedReader()){
        val t = readln().toInt()
        for (turn in 0 until  t){
            val n = readln().toInt()
            val lst = readln().split(" ").map { it.toInt() }

            val queue = PriorityQueue<Long>().apply {
                for(element in lst){
                    add(element.toLong())
                }
            }

            var result = 0L
            for (i in 0 until n - 1){
                val first = queue.poll()
                val second = queue.poll()

                result += (first + second)
                queue.add(first + second)
            }

            println(result)
        }
    }
}

fun main(){
    Solution13975.solution()
}