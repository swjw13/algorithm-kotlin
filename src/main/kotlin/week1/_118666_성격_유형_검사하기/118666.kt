package `118666_성격_유형_검사하기`

private class Solution {
    fun solution(survey: Array<String>, choices: IntArray): String {
        val answer = java.lang.StringBuilder()
        val score = mutableMapOf<Char, Int>()
        "RTCFJMAN".forEach { score[it] = 0 }

        for (i in survey.indices) {
            if (choices[i] <= 3) {
                score[survey[i][0]] = score[survey[i][0]]!! + (4 - choices[i])
            } else if (choices[i] >= 5) {
                score[survey[i][1]] = score[survey[i][1]]!! + (choices[i] - 4)
            }
        }

        answer.append(if (score['R']!! >= score['T']!!) "R" else "T")
        answer.append(if(score['C']!! >= score['F']!!) "C" else "F")
        answer.append(if (score['J']!! >= score['M']!!) "J" else "M")
        answer.append(if (score['A']!! >= score['N']!!) "A" else "N")

        return answer.toString()
    }
}