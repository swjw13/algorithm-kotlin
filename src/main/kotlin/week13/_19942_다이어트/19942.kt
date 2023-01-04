package week13._19942_다이어트

import kotlin.math.pow

data class Nutrient(
    val one: Int,
    val two: Int,
    val three: Int,
    val four: Int
)

data class Food(
    val nutrient: Nutrient,
    val price: Int
)

fun main() = with(System.`in`.bufferedReader()) {
    var resPrice = Int.MAX_VALUE
    var resList = mutableListOf<Int>()

    val n = readln().toInt()
    val foodList = mutableListOf<Food>()

    val (mp, mf, ms, mv) = readln().split(" ").map { it.toInt() }
    for (i in 0 until n) {
        val (p, f, s, v, c) = readln().split(" ").map { it.toInt() }
        foodList.add(Food(Nutrient(p, f, s, v), c))
    }

    (1 until 2.0.pow(n.toDouble()).toInt()).forEach {
        var pValue = 0
        var fValue = 0
        var sValue = 0
        var vValue = 0
        var price = 0
        val tmp = mutableListOf<Int>()
        numToFormattedBinary(it, n).forEachIndexed { index, c ->
            if (c == '1') {
                pValue += foodList[index].nutrient.one
                fValue += foodList[index].nutrient.two
                sValue += foodList[index].nutrient.three
                vValue += foodList[index].nutrient.four
                price += foodList[index].price
                tmp.add(index + 1)
            }
        }

        if (pValue >= mp && fValue >= mf && sValue >= ms && vValue >= mv) {
            if (price < resPrice) {
                resPrice = price
                resList = tmp
            } else if (price == resPrice) {
                if (tmp.before(resList)) resList = tmp
            }
        }
    }

    if (resPrice == Int.MAX_VALUE){
        println(-1)
    } else {
        println(resPrice)
        println(resList.joinToString(" "))
    }
}

fun List<Int>.before(other: List<Int>): Boolean{
    for (i in 0 until minOf(this.size, other.size)){
        if (this[i] < other[i]) return true
        else if (this[i] > other[i]) return false
    }

    return this.size <= other.size
}

fun numToFormattedBinary(num: Int, n: Int): String {
    var res = ""
    var cnt = num
    while (cnt > 0) {
        res = (cnt % 2).toString() + res
        cnt /= 2
    }
    while (res.length < n) {
        res = "0$res"
    }

    return res
}