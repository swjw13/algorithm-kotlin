package week2._1406_에디터

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

/**
 * stack 사용
 * listIterator 사용 (그냥 iterator와는 다르다.) + python (generator + Iterator)
 * 사람들 풀이 한번 찾아보기
 */

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val words = reader.readLine().toList()

    val a: LinkedList<String> = LinkedList()
    words.forEach { a.add(it.toString()) }

    val m = reader.readLine().toInt()
    var curIdx = words.size

    for (i in 0 until m) {
        val curAction = reader.readLine().split(" ")
        if (curAction.size == 2) {
            a.add(curIdx, curAction[1])
            curIdx += 1
        } else {
            if (curAction[0] == "L") {
                if (curIdx > 0) curIdx -= 1
            } else if (curAction[0] == "D") {
                if (curIdx <= a.size) curIdx += 1
            } else if (curAction[0] == "B") {
                if (curIdx > 0) {
                    a.removeAt(curIdx - 1)
                    curIdx -= 1
                }
            }
        }
    }

    val s = java.lang.StringBuilder()
    a.forEach {
        s.append(it)
    }
    println(s.toString())
}