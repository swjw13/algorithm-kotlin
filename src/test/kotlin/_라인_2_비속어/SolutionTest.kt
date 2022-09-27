package _라인_2_비속어

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class SolutionTest {
    private lateinit var solution: Solution

    @Before
    fun init() {
        solution = Solution()
    }

    @Test
    fun ex1() {
        val k = 2
        val dic = listOf("slang", "badword")
        val chat = "badword ab.cd bad.ord .word sl.. bad.word"

        val result = "####### ab.cd ####### .word #### bad.word"
        assertEquals(solution.solution(k, dic, chat), result)
    }

    @Test
    fun ex2() {
        val k = 3
        val dic = listOf("abcde", "cdefg", "efgij")
        val chat = ".. ab. cdefgh .gi. .z."

        val result = "## ### cdefgh #### .z."
        assertEquals(solution.solution(k, dic, chat), result)
    }
}