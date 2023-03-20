package week22

import java.lang.StringBuilder

/**
 * 구현, 시물레이션
 * 1 <= x <= 1,000,000,000 이길래 반복이 등장할 것이란 생각을 가지고 문제를 해결
 * 반복되는 구간을 활용해 시뮬레이션 횟수를 줄임
 * visited 탐색을 줄이기 위해 set을 추가적으로 활용
 */
private class Solution9519 {

    fun rollbackWord(word: String): String {
        val frontSb = StringBuilder()
        val backSb = StringBuilder()

        word.forEachIndexed { index, c ->
            if (index % 2 == 0) frontSb.append(c)
            else backSb.append(c)
        }

        return frontSb.toString() + backSb.reversed()
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        var n = readln().toInt()
        var word = readln().trim()

        var turn = 0
        val visitedList = mutableListOf<String>()
        val visited = mutableSetOf<String>()

        while (true) {
            word = rollbackWord(word)
            turn += 1

            if (turn == n) {
                println(word)
                return@with
            }

            if (word in visited) {
                val firstOccurTurn = visitedList.indexOf(word) + 1
                n -= firstOccurTurn

                println(visitedList[firstOccurTurn - 1 + n % (turn - firstOccurTurn)])
                return@with
            } else {
                visitedList.add(word)
                visited.add(word)
            }
        }
    }
}

fun main() {
    Solution9519().solution()
}