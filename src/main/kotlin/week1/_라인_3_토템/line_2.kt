package week1._라인_3_토템

import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.math.abs

class Solution {
    fun solution(n: Int, m: Int, fires: List<List<Int>>, ices: List<List<Int>>): List<List<Int>> {
        val res = MutableList(n) { MutableList(n) { 0 } }

        for (point in fires) {
            val (row, col) = point
            val curBoard = MutableList(n) { MutableList(n) { 0 } }

            var minRow = row - 1
            var maxRow = row - 1
            var minCol = col - 1
            var maxCol = col - 1

            // 새로운 point 가 1이 되는 횟수
            val cnt = maxOf(row - 1, n - row, col - 1, n - col)

            if (cnt >= m) {
                for (i in 0 until m) {
                    minRow = max(minRow - 1, 0)
                    maxRow = min(maxRow + 1, n - 1)
                    minCol = max(minCol - 1, 0)
                    maxCol = min(maxCol + 1, n - 1)

                    for (r in minRow..maxRow) {
                        for (c in minCol..maxCol) {
                            curBoard[r][c] += 1
                        }
                    }
                }
                for (r in 0 until n) {
                    for (c in 0 until n) {
                        res[r][c] += curBoard[r][c]
                    }
                }
            } else {
                for (i in 0 until cnt) {
                    minRow = max(minRow - 1, 0)
                    maxRow = min(maxRow + 1, n - 1)
                    minCol = max(minCol - 1, 0)
                    maxCol = min(maxCol + 1, n - 1)

                    for (r in minRow..maxRow) {
                        for (c in minCol..maxCol) {
                            curBoard[r][c] += 1
                        }
                    }
                }
                for (r in 0 until n) {
                    for (c in 0 until n) {
                        res[r][c] += (curBoard[r][c] + (m - cnt))
                    }
                }
            }
        }

        for (point in ices) {
            val (row, col) = point

            for (r in 0 until n) {
                for (c in 0 until n) {
                    if (r == row - 1 && c == col - 1) {
                        res[r][c] -= m
                    } else {
                        val dist = abs(r - row + 1) + abs(c - col + 1) - 1
                        res[r][c] -= max(0, m - dist)
                    }
                }
            }
        }

        return res
    }
}