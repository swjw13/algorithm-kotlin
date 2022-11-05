package week6._1541_괄호

fun eval(expr: String): Int {
    val numberRegex = Regex("[0-9]+")
    val opRegex = Regex("[+-]")

    val numbers = numberRegex
        .findAll(expr)
        .map { it.value }
        .toList()

    val ops = opRegex.findAll(expr)
        .map {
            it.value
        }
        .toList()

    var curNumber = numbers.first().toInt()
    for (i in ops.indices) {
        if (ops[i] == "+") {
            curNumber += numbers[i + 1].toInt()
        } else {
            curNumber -= numbers[i + 1].toInt()
        }
    }
    return curNumber
}

fun main() = with(System.`in`.bufferedReader()) {
    val expr = readLine().split("-").map { eval(it).toString() }
    println(eval(expr.joinToString("-")))
}