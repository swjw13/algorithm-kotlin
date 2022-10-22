package week4._11100_강의실_배정

/**
 * heap.offer()
 */

import java.util.PriorityQueue

fun main() {
    val heap: PriorityQueue<List<Int>> = PriorityQueue { o1, o2 ->
        if ((o1[0] < o2[0]) or (o1[0] == o2[0] && o1[1] < o2[1])) -1
        else 1
    }
    val numberHeap: PriorityQueue<Int> = PriorityQueue()

    with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        for (i in 0 until n) {
            heap.add(readLine().split(" ").map { it.toInt() })
        }

        val first = heap.poll()
        numberHeap.add(first[1])

        while (heap.size != 0) {
            val curLst = heap.poll()
            if (curLst[0] >= numberHeap.peek()) {
                numberHeap.poll()
            }
            numberHeap.add(curLst[1])
        }
        println(numberHeap.size)
    }
}