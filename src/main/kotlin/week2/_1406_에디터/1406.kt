package week2._1406_에디터

import java.util.LinkedList

/**
 * stack 사용
 * listIterator 사용 (그냥 iterator와는 다르다.) + python (generator + Iterator)
 * 사람들 풀이 한번 찾아보기
 */

fun main() {

    with(System.`in`.bufferedReader()) {
        val res = LinkedList<Char>()
        readLine().forEach {
            res.add(it)
        }

        val iterator = res.listIterator()

        while(iterator.hasNext()){
            iterator.next()
        }

        val n = readLine().toInt()
        for (i in 0 until n) {
            val curAction = readLine().split(" ")

            when (curAction[0]){
                "L" -> if (iterator.hasPrevious()) iterator.previous()
                "D" -> if (iterator.hasNext()) iterator.next()
                "B" -> if (iterator.hasPrevious()) {
                    iterator.previous()
                    iterator.remove()
                }
                else -> iterator.add(curAction[1][0])
            }
        }
        println(res.toCharArray())
    }
}