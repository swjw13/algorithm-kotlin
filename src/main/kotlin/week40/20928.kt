package week40

private object Solution20928 {
    fun solution() = with(System.`in`.bufferedReader()){
        val (n, m) = readln().split(" ").map { it.toInt() }
        val pList = readln().split(" ").map { it.toInt() }
        val xList = readln().split(" ").map { it.toInt() }

        var curIdx = 0
        var cnt = 0
        while (true) {
            val curPoint = pList[curIdx]
            val curDist = xList[curIdx]

            /**
             * 목적지에 도달할 수 있는 경우
             */
            if (curPoint + curDist >= m) {
                println(cnt)
                return@with
            }

            curIdx += 1
            var mostAwayDistance = -1
            var nextIdx = -1
            while (curIdx < n && pList[curIdx] <= curPoint + curDist){
                if (mostAwayDistance <= pList[curIdx] + xList[curIdx]){
                    mostAwayDistance = pList[curIdx] + xList[curIdx]
                    nextIdx = curIdx
                }
                curIdx += 1
            }

            if (nextIdx == -1){
                println(-1)
                return@with
            }

            curIdx = nextIdx
            cnt += 1
        }
    }
}

fun main(){
    Solution20928.solution()
}