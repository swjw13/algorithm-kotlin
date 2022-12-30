package week12._21758_꿀_따기

fun main() = with(System.`in`.bufferedReader()) {
    var res = 0
    val n = readln().toInt()
    val lst = readln().split(" ").map { it.toInt() }.toMutableList()
    val totalLst = MutableList(n) { 0 }
    totalLst[0] = lst[0]

    var maxIdx = -1
    var maxValue = 0

    for (idx in 1 until n) {
        totalLst[idx] = totalLst[idx - 1] + lst[idx]
        if (idx != n - 1 && lst[idx] > maxValue) {
            maxIdx = idx
            maxValue = lst[idx]
        }
    }

    // 벌이 서로 다른 방향에 있을 때
    res = maxOf(res, totalLst[maxIdx] - totalLst[0] + totalLst[n - 2] - totalLst[maxIdx - 1])

    // 꿀이 가장 오른쪽에 있을 때
    var tmp = totalLst.last() - totalLst[0]
    for (idx in 1 until n - 1){
        res = maxOf(res, tmp + totalLst.last() - totalLst[idx] - lst[idx])
    }

    // 꿀이 가장 왼쪽에 있을 때
    tmp = totalLst[n - 2]
    for (idx in 1 until n - 1){
        res = maxOf(res, tmp + totalLst[idx - 1] - lst[idx])
    }

    println(res)
}