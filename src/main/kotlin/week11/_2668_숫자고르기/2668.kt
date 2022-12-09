package week11._2668_숫자고르기

fun main() = with(System.`in`.bufferedReader()) {
    val ans = mutableListOf<Int>()

    val n = readln().toInt()
    val edgeMap = mutableMapOf<Int, Int>()
    for (i in 1..n) {
        val connected = readln().toInt()
        edgeMap[i] = connected
    }

    val visited = MutableList(n + 1) { false }
    for (i in 1..n) {
        if (visited[i].not()){
            val track = mutableListOf<Int>()
            var curPoint = i
            while (visited[curPoint].not()){
                track.add(curPoint)
                visited[curPoint] = true

                curPoint = edgeMap[curPoint]!!
            }

            if(curPoint in track) {
                val cycleTrack = track.subList(track.indexOf(curPoint), track.size)
                ans.addAll(cycleTrack)
            }
        }
    }

    ans.sort()
    println(ans.size)
    ans.forEach {
        println(it)
    }
}