package week3._92341_주차요금계산

import kotlin.math.ceil

class Solution {
    private fun timeToInt(time: String): Int {
        val (hour, minute) = time.split(":").map { it.toInt() }
        return hour * 60 + minute
    }

    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val answer = mutableListOf<Int>()

        val result = mutableMapOf<String, Int>()
        val endingTime = timeToInt("23:59")

        val checkingMachine = mutableMapOf<String, Int>()

        records.forEach {
            val (timeString, carNumber, state) = it.split(" ")
            val time = timeToInt(timeString)

            if (state == "IN") {
                checkingMachine[carNumber] = time
            } else {
                result[carNumber] = (result[carNumber] ?: 0) + (time - checkingMachine[carNumber]!!)
                checkingMachine.remove(carNumber)
            }
        }

        checkingMachine.forEach {
            val carNumber = it.key
            result[carNumber] = (result[carNumber] ?: 0) + (endingTime - it.value)
        }

        result.toSortedMap().values.forEach {
            if (it < fees[0]) {
                answer.add(fees[1])
            } else {
                answer.add(fees[1] + ceil((it - fees[0]).toFloat()/fees[2].toFloat()).toInt() * fees[3])
            }
        }

        return answer.toIntArray()
    }
}