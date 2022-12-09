package week11._1240_노드사이의거리

fun main() = with(System.`in`.bufferedReader()) {
//    val (n, m) = readln().split(" ").map { it.toInt() }
//    val distanceMap = List(n + 1) { MutableList(n + 1) { 1000000 } }
//
//    for (i in 0 until n - 1){
//        val (p1, p2, dist) = readln().split(" ").map { it.toInt() }
//        distanceMap[p1][p2] = dist
//        distanceMap[p2][p1] = dist
//    }
//
//    for (k in 1 .. n) {
//        for (i in 1..n) {
//            for (j in 1..n) {
//                distanceMap[i][j] = minOf(distanceMap[i][j], distanceMap[i][k] + distanceMap[k][j])
//            }
//        }
//    }
//
//    for (i in 0 until m){
//        val (p1, p2) = readln().split(" ").map { it.toInt() }
//        println(distanceMap[p1][p2])
//    }
    val (n, m) = readln().split(" ").map { it.toInt() }

    val distanceMap = mutableMapOf<Int, MutableMap<Int, Int>>()
    for (i in 0 until n - 1){
        val (p1, p2, dist) = readln().split(" ").map { it.toInt() }
        if (distanceMap[p1] == null) distanceMap[p1] = mutableMapOf()
        if (distanceMap[p2] == null) distanceMap[p2] = mutableMapOf()

        distanceMap[p1]!![p2] = dist
        distanceMap[p2]!![p1] = dist
    }

    val visited = MutableList(n + 1){false}
    for (i in 0 until m){
        val (p1, p2) = readln().split(" ").map { it.toInt() }
        visited.init()
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(p1, 0))
        visited[p1] = true

        while(queue.isNotEmpty()){
            val curPoint = queue.removeFirst()

            if(curPoint.first == p2) {
                println(curPoint.second)
                break
            }

            for (connected in distanceMap[curPoint.first]!!.keys){
                if (visited[connected].not()){
                    visited[connected] = true
                    queue.add(Pair(connected, curPoint.second + distanceMap[curPoint.first]!![connected]!!))
                }
            }
        }
    }
}

fun MutableList<Boolean>.init(){
    for (i in this.indices){
        this[i] = false
    }
}