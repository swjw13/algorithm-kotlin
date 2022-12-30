package week12._13335_트럭

import java.util.*

class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: List<Int>): Int {
        var answer = 0

        val bridge: Queue<Int> = LinkedList()
        for(i in 1..bridge_length) bridge.add(0)
        var total_weight = 0

        val remain_trucks = truck_weights.toMutableList()

        while (true) {
            if (bridge.sum() == 0 && remain_trucks.isEmpty()) break

            // 큐의 맨 앞 값을 리턴
            if (bridge.element() == 0) {
                if(remain_trucks.isEmpty()){
                    val tmp = bridge.remove()
                    total_weight -= tmp
                    bridge.add(0)
                } else{
                    if (total_weight + remain_trucks[0] <= weight) {
                        bridge.remove()
                        bridge.add(remain_trucks[0])

                        total_weight += remain_trucks[0]
                        remain_trucks.removeAt(0)

                    } else {
                        bridge.remove()
                        bridge.add(0)
                    }
                }
                answer += 1
            } else {
                if(remain_trucks.isEmpty()){
                    val tmp = bridge.remove()
                    total_weight -= tmp
                    bridge.add(0)
                } else{
                    if(total_weight - bridge.element() + remain_trucks[0] <= weight){
                        val tmp = bridge.remove()
                        total_weight -= tmp

                        bridge.add(remain_trucks[0])
                        total_weight += remain_trucks[0]
                        remain_trucks.removeAt(0)
                    } else{
                        val tmp = bridge.remove()
                        total_weight -= tmp
                        bridge.add(0)
                    }
                }

                answer += 1
            }

        }

        return answer
    }
}

fun main() = with(System.`in`.bufferedReader()){
    val (_, w, l) = readln().split(" ").map { it.toInt() }
    val lst = readln().split(" ").map { it.toInt() }

    println(Solution().solution(w, l, lst))
}