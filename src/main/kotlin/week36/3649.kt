package week36

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private object Solution3649 {
    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))

        var s: String? = null
        while ((br.readLine().also { s = it }) != null) {
            val numbers = mutableListOf<Int>()
            val x = s!!.toInt() * 10_000_000
            val n = br.readLine().toInt()
            for (i in 0 until n){
                numbers.add(br.readLine().toInt())
            }

            numbers.sort()
            var start = 0
            var end = n - 1
            while (end > start){
                val startNum = numbers[start]
                val endNum = numbers[end]
                if (startNum + endNum == x){
                    break
                } else if (startNum + endNum < x) {
                    start += 1
                } else {
                    end -= 1
                }
            }

            if (end > start) println("yes ${numbers[start]} ${numbers[end]}")
            else println("danger")
        }
        bw.flush()
        bw.close()
    }
}

fun main(){
    Solution3649.solution()
}
