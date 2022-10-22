package week4._92342_양궁

/**
 * list.clone 으로 값 복사하기
 */

class Solution {

    fun solution(n: Int, info: IntArray): IntArray {
        val res = MutableList(11) { 0 }

        val answer = MutableList(11) { 0 }
        var totalCount = 0

        fun scoring() {
            val myCount = (0..10)
                .filter { res[it] + info[it] != 0 && res[it] > info[it] }
                .sumOf { 10 - it }
            val yourCount = (0..10)
                .filter { res[it] + info[it] != 0 && res[it] <= info[it] }
                .sumOf { 10 - it }

            if ((myCount - yourCount) > totalCount) {
                totalCount = myCount - yourCount
                for (i in answer.indices) {
                    answer[i] = res[i]
                }
            } else if (myCount - yourCount == totalCount) {
                if (answer.compare(res)) {
                    for (i in answer.indices) {
                        answer[i] = res[i]
                    }
                }
            }
        }

        fun backTracking(curIdx: Int, remain: Int) {
            if (remain == 0) {
                scoring()
                return
            }

            if (curIdx == 10) {
                res[curIdx] = remain
                scoring()
                res[curIdx] = 0
                return
            }

            for (i in remain downTo 0) {
                res[curIdx] = i
                backTracking(curIdx + 1, remain - i)
            }
        }

        backTracking(0, n)
        return if (totalCount == 0) {
            intArrayOf(-1)
        } else {
            println(answer)
            answer.toIntArray()
        }
    }
}

fun List<Int>.compare(other: List<Int>): Boolean {
    for (i in other.size - 1 downTo 0) {
        if (other[i] + this[i] != 0) {
            if (other[i] < this[i]) return false
            else if (other[i] > this[i]) return true
        }
    }
    return false
}