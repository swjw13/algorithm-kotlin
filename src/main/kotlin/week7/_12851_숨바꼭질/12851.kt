package week7._12851_숨바꼭질

data class Node(
    val point: Int,
    val dist: Int
)

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }

    if (n >= k) {
        println(n - k)
        println(1)
        return@with
    }

    val dist = MutableList(100001) { Int.MAX_VALUE }
    val queue = ArrayDeque<Node>()

    dist[n] = 0
    queue.add(Node(n, 0))
    var cnt = 0

    while (queue.isNotEmpty()) {
        val curNode = queue.removeFirst()

        val curPoint = curNode.point
        val curDist = curNode.dist

        if (curPoint == k) {
            if (curDist < dist[k]){
                cnt = 1
                dist[k] = curDist
            } else if (curDist == dist[k]){
                cnt += 1
            }
            continue
        }

        if (curPoint + 1 < 100001 && dist[curPoint+ 1] >= curDist + 1){
            dist[curPoint + 1] = curDist + 1
            queue.add(Node(curPoint + 1, curDist + 1))
        }
        if (curPoint - 1 >= 0 && dist[curPoint - 1] >= curDist + 1){
            dist[curPoint - 1] = curDist + 1
            queue.add(Node(curPoint - 1, curDist + 1))
        }
        if (curPoint * 2 < 100001 && dist[curPoint * 2] >= curDist + 1){
            dist[curPoint * 2] = curDist + 1
            queue.add(Node(curPoint * 2, curDist + 1))
        }

    }

    println(dist[k])
    println(cnt)
}