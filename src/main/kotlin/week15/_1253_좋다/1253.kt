package week15._1253_좋다

fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    val lst = readln().split(" ").map { it.toInt() }
    val numberMap = hashMapOf<Int, Int>()
    val zeroCnt = lst.filter { it == 0 }.size

    for (i in 0 until n) {
        for (j in i + 1 until n) {
            val newNumber = lst[i] + lst[j]

            if (newNumber !in numberMap.keys) numberMap[newNumber] = 1
            else numberMap[newNumber] = numberMap[newNumber]!! + 1
        }
    }

    var cnt = 0
    for (number in lst) {
        if (number in numberMap.keys) {
            if (number == 0){
                if (numberMap[number]!! > zeroCnt - 1) cnt += 1
            } else {
                if (numberMap[number]!! > zeroCnt) cnt += 1
            }
        }
    }
    println(cnt)
}