package week17._1043_거짓말

/**
 * 분리 집합 -> union - find
 */
private class Solution1043 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val people = readln().split(" ").map { it.toInt() }

        val partyPerPeople = MutableList(n + 1) { mutableSetOf<Int>() }
        val partyParticipant = mutableListOf(listOf<Int>())
        val visited = MutableList(m + 1) { false }

        for (i in 0 until m) {
            val curParty = readln().split(" ").map { it.toInt() }.toMutableList()
            curParty.removeFirst()
            curParty.forEach {
                partyPerPeople[it].add(i + 1)
            }
            partyParticipant.add(curParty)
        }

        val queue = ArrayDeque<Int>().apply {
            for (idx in 1 until people.size) {
                partyPerPeople[people[idx]].forEach {
                    add(it)
                    visited[it] = true
                }
            }
        }

        while (queue.isNotEmpty()) {
            val curParty = queue.removeFirst()

            for (participant in partyParticipant[curParty]){
                for (party in partyPerPeople[participant]){
                    if (visited[party].not()){
                        visited[party] = true
                        queue.add(party)
                    }
                }
            }
        }

        println(visited.filter { !it }.size - 1)
    }
}

fun main() {
    Solution1043().solution()
}