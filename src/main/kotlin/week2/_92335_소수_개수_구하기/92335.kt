package week2._92335_소수_개수_구하기

import kotlin.math.sqrt


/**
 * str.toString(k) -> k 진법으로 변환
 * Long 타입 준비하기
 * (범위).none 함수 사용 해 보기
 */
class Solution {

    fun numberToString(n: Int, k: Int): String {
        var number = n
        var res = ""
        while (number > 0) {
            res = (number % k).toString() + res
            number /= k
        }
        return res
    }

    fun prime(n: Long): Boolean {
        if (n == 1L) return false
        for (i in 2..sqrt(n.toFloat()).toInt()) {
            if (n % i == 0L) return false
        }
        return true
    }

    fun solution(n: Int, k: Int): Int {
        var answer: Int = 0

        val numberString = numberToString(n, k).split(Regex("0+"))

        for (number in numberString){
            if (number.isNotEmpty() && prime(number.toLong())) {
                answer += 1
            }
        }

        return answer
    }
}
