package week16._1967_트리의지름

fun main() = with(System.`in`.bufferedReader()) {
    var res = -Int.MAX_VALUE
    val n = readln().toInt()

    if(n == 1){
        println(0)
        return@with
    }

    val tree = MutableList(n + 1) { mutableListOf<Pair<Int, Int>>() }
    for (i in 0 until n - 1) {
        val (p, c, w) = readln().split(" ").map { it.toInt() }
        tree[p].add(Pair(c, w))
    }

    fun dfs(curPoint: Int): Int {
        when (tree[curPoint].size) {
            0 -> return 0

            1 -> {
                val result = dfs(tree[curPoint][0].first)
                res = maxOf(res, result + tree[curPoint][0].second)
                return result + tree[curPoint][0].second
            }

            else -> {
                val prevLengthList = mutableListOf<Int>()
                for (i in 0 until tree[curPoint].size){
                    val result = dfs(tree[curPoint][i].first)
                    prevLengthList.add(result + tree[curPoint][i].second)
                }
                prevLengthList.sortByDescending { it }
                res = maxOf(res, prevLengthList[0] + prevLengthList[1])

                return prevLengthList[0]
            }
        }
    }

    dfs(1)
    println(res)
}