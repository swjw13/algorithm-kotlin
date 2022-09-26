package `118666_성격_유형_검사하기`

class Solution {
    fun solution(survey: Array<String>, choices: IntArray): String {
        var answer: String = ""
        val score = mutableMapOf<Char, Int>()
        "RTCFJMAN".forEach { score[it] = 0 }

        for (i in survey.indices) {
            if (choices[i] <= 3) {
                score[survey[i][0]] = score[survey[i][0]]!! + (4 - choices[i])
            } else if (choices[i] >= 5) {
                score[survey[i][1]] = score[survey[i][1]]!! + (choices[i] - 4)
            }
        }

        answer += if (score['R']!! >= score['T']!!) "R" else "T"
        answer += if(score['C']!! >= score['F']!!) "C" else "F"
        answer += if (score['J']!! >= score['M']!!) "J" else "M"
        answer += if (score['A']!! >= score['N']!!) "A" else "N"

        return answer
    }
}