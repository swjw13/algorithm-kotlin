package week42

private object Solution2190 {
    fun solution() = with(System.`in`.bufferedReader()){
        val (n, a, b) = readln().split(" ").map { it.toInt() }
        val pointList = mutableListOf<Pair<Long, Long>>()
        val xList = mutableSetOf<Long>()
        val yList = mutableSetOf<Long>()

        for (i in 0 until n){
            val(x, y) = readln().split(" ").map { it.toLong() }
            pointList.add(Pair(x, y))
            xList.add(x)
            yList.add(y)
        }

        var result = 0
        for (x in xList) {
            for (y in yList){
                var cnt = 0
                val xRange = x ..x + a
                val yRange = y .. y + b
                for (point in pointList){
                    if (isInRange(point.first, xRange) && isInRange(point.second, yRange)) cnt += 1
                }

                result = maxOf(result, cnt)
            }
        }

        println(result)
    }

    fun isInRange(target: Long, range: LongRange): Boolean {
        return target in range
    }
}

fun main(){
    Solution2190.solution()
}