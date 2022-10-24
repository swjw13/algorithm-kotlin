package week5._1326_폴짝폴짝

fun main() {

    val n = readln().toInt()
    val lst = readln().trim().split(" ").map { it.toInt() }.toMutableList()
    lst.add(0, 0)
    val (a, b) = readln().trim().split(" ").map { it.toInt() }

    val queue = ArrayDeque<Int>()
    queue.add(a)
    val distance = MutableList(n + 1) { Int.MAX_VALUE }
    distance[a] = 0

    while (queue.isNotEmpty()) {
        val curIdx = queue.removeFirst()
        var newIdx = curIdx + lst[curIdx]
        while (newIdx <= n) {
            if (distance[newIdx] > distance[curIdx] + 1) {
                distance[newIdx] = distance[curIdx] + 1
                queue.add(newIdx)
            }
            newIdx += lst[curIdx]
        }

        newIdx = curIdx - lst[curIdx]
        while (newIdx > 0) {
            if (distance[newIdx] > distance[curIdx] + 1) {
                distance[newIdx] = distance[curIdx] + 1
                queue.add(newIdx)
            }
            newIdx -= lst[curIdx]
        }
    }

    if (distance[b] == Int.MAX_VALUE) println(-1)
    else println(distance[b])
}