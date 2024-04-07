package week40

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.BitSet

private object Solution13701 {
    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val aList = br.readLine().split(" ")
        val visited = HashSet<Int>()
        for (a in aList){
            val number = a.toInt()
            if(visited.contains(number).not()){
                visited.add(number)
                print("$a ")
            }
        }
    }
}

fun main() {
    Solution13701.solution()
}