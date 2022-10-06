package _라인_3_토템

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import week1._라인_3_토템.Solution

class SolutionTest {
    private lateinit var solution: Solution

    @Before
    fun init() {
        solution = Solution()
    }

    @Test
    fun solution1() {
        val n = 3
        val m = 2
        val fires = listOf(listOf(1, 1))
        val ices = listOf(listOf(3, 3))

        val result = listOf(
            listOf(2, 2, 0),
            listOf(2, 1, -1),
            listOf(0, -1, -1)
        )
        assertEquals(result, solution.solution(n, m, fires, ices))
    }

    @Test
    fun solution2() {
        val n = 5
        val m = 3
        val fires = listOf(listOf(5, 5), listOf(1, 3), listOf(5, 2))
        val ices = listOf(listOf(1, 5), listOf(3, 2))

        val result = listOf(
            listOf(1, 0, 0, 0, -1),
            listOf(1, 2, 2, 2, 1),
            listOf(1, 2, 3, 3, 2),
            listOf(2, 2, 4, 5, 4),
            listOf(2, 2, 4, 5, 4)
        )
        assertEquals(result, solution.solution(n, m, fires, ices))
    }
}