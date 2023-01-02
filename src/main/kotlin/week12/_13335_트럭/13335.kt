package week12._13335_트럭

import java.util.*

class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: List<Int>): Int {
        var answer = 0

        val bridge: Queue<Int> = LinkedList()
        for (i in 1..bridge_length) bridge.add(0)
        var totalWeight = 0

        val remainTrucks = truck_weights.toMutableList()

        while (true) {
            if (bridge.sum() == 0 && remainTrucks.isEmpty()) break

            // 큐의 맨 앞 값을 리턴
            if (bridge.element() == 0) {
                if (remainTrucks.isEmpty()) {
                    val tmp = bridge.remove()
                    totalWeight -= tmp
                    bridge.add(0)
                } else {
                    if (totalWeight + remainTrucks[0] <= weight) {
                        bridge.remove()
                        bridge.add(remainTrucks[0])

                        totalWeight += remainTrucks[0]
                        remainTrucks.removeAt(0)

                    } else {
                        bridge.remove()
                        bridge.add(0)
                    }
                }
                answer += 1
            } else {
                if (remainTrucks.isEmpty()) {
                    val tmp = bridge.remove()
                    totalWeight -= tmp
                    bridge.add(0)
                } else {
                    if (totalWeight - bridge.element() + remainTrucks[0] <= weight) {
                        val tmp = bridge.remove()
                        totalWeight -= tmp

                        bridge.add(remainTrucks[0])
                        totalWeight += remainTrucks[0]
                        remainTrucks.removeAt(0)
                    } else {
                        val tmp = bridge.remove()
                        totalWeight -= tmp
                        bridge.add(0)
                    }
                }

                answer += 1
            }

        }

        return answer
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (_, w, l) = readln().split(" ").map { it.toInt() }
    val lst = readln().split(" ").map { it.toInt() }

    println(Solution().solution(w, l, lst))
}