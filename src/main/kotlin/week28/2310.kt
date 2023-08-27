package week28

private class Solution2310 {

    data class QueueElement(
        val remainCoin: Int,
        val roomNo: Int
    )

    fun solution() = with(System.`in`.bufferedReader()) {

        loop@ while (true) {
            val n = readln().toInt()
            if (n == 0) return@with

            val roomStyle = mutableListOf("")
            val coin = mutableListOf(0)
            val connected = MutableList(n + 1) { mutableListOf<Int>() }

            for (i in 0 until n) {
                val lst = readln().trim().split(" ")
                roomStyle.add(lst[0])
                coin.add(lst[1].toInt())
                for (j in 2 until lst.size - 1) {
                    connected[i + 1].add(lst[j].toInt())
                }
            }

            val visited = MutableList(n + 1) { -1 }
            val queue = ArrayDeque<QueueElement>().apply {
                visited[1] = 0
                add(QueueElement(0, 1))
            }

            while (queue.isNotEmpty()) {
                val curPoint = queue.removeFirst()

                val curCoin = curPoint.remainCoin
                val curRoom = curPoint.roomNo

                val remainCoin = if (roomStyle[curRoom] == "E") curCoin else if (roomStyle[curRoom] == "L") maxOf(coin[curRoom], curCoin) else curCoin - coin[curRoom]

                if (curRoom == n) {
                    if (remainCoin >= 0) {
                        println("Yes")
                        continue@loop
                    } else{
                        continue
                    }
                }

                if (remainCoin >= 0) {
                    for (con in connected[curRoom]) {
                        if (remainCoin > visited[con]) {
                            visited[con] = remainCoin
                            queue.add(QueueElement(remainCoin, con))
                        }
                    }
                }
            }

            println("No")
        }
    }
}

fun main() {
    Solution2310().solution()
}