package week3._16987_계란으로_바위치기

import kotlin.math.max

/**
 * https://www.acmicpc.net/problem/16987
 * 해결 방법: BackTracking
 * 방법 결정 이유: 전체 갯수인 8 ** 8 이 접근 가능해 보여서 사용하게 되었다.
 * 트러블 슈팅
 *  - lst를 변수로 전달할 수 있으면 쉽게 풀리겠지만, Java의 특성상 Call By Reference 로 동작하기 때문에 어려움을 겪음
 *  - 경우의 수를 좀 더 자세히 살표 볼 필요가 있음(처음에는 lst[i] > 0 인 조건을 넣지 않음)
 */

var res = 0

fun main() {

    val weightMap = mutableMapOf<Int, Int>()
    val lst = mutableListOf<Int>()

    fun brokenCount(): Int = lst.filter { it <= 0 }.size

    fun backtracking(idx: Int) {
        if (idx == lst.size) {
            res = max(res, brokenCount())
        } else {
            if (lst[idx] <= 0) {
                backtracking(idx + 1)
            } else if (brokenCount() == lst.size - 1) {
                res = max(res, lst.size - 1)
            } else {
                for (i in 0 until lst.size) {
                    if (i != idx && lst[i] > 0) {
                        lst[idx] -= weightMap[i]!!
                        lst[i] -= weightMap[idx]!!

                        backtracking(idx + 1)

                        lst[idx] += weightMap[i]!!
                        lst[i] += weightMap[idx]!!
                    }
                }
            }
        }
    }

    with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        for (i in 0 until n) {
            val (strength, weight) = readLine().split(" ").map { it.toInt() }
            weightMap[i] = weight
            lst.add(strength)
        }

        backtracking(0)

        println(res)
    }
}