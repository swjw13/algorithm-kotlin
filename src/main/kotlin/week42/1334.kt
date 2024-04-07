package week42

private object Solution1334 {
    fun solution() {
        val num = readln().trim()
        if (num.length % 2 == 0) {
            val frontWord = num.substring(0 until num.length / 2)
            val backWord = num.substring(num.length / 2)

            if (frontWord.reversed().isBiggerThan(backWord)) {
                println("${frontWord}${frontWord.reversed()}")
            } else {
                val added = frontWord.plusOne()
                if (added.length == frontWord.length) {
                    println("${added}${added.reversed()}")
                } else {
                    val middle = added.last()
                    val removed = added.dropLast(1)

                    println("${removed}${middle}${removed.reversed()}")
                }
            }
        } else {
            val frontWord = num.substring(0..num.length / 2)
            val backWord = num.substring(num.length / 2)

            if (frontWord.reversed().isBiggerThan(backWord)) {
                val middle = frontWord.last()
                val removed = frontWord.dropLast(1)

                println("${removed}${middle}${removed.reversed()}")
            } else {
                val added = frontWord.plusOne()

                if (added.length != frontWord.length) {
                    val removed = added.dropLast(1)
                    println("${removed}${removed.reversed()}")
                } else {
                    val middle = added.last()
                    val removed = added.dropLast(1)

                    println("${removed}${middle}${removed.reversed()}")
                }
            }
        }
    }

    fun String.isBiggerThan(b: String): Boolean {
        if (this.length > b.length) return true
        else if (this.length < b.length) return false
        else {
            for (idx in this.indices) {
                if (this[idx].toString().toInt() > b[idx].toString().toInt()) return true
                else if (this[idx].toString().toInt() < b[idx].toString().toInt()) return false
            }
            return false
        }
    }

    fun String.plusOne(): String {
        val reversed = this.reversed()
        val sb = StringBuilder()
        var more = 1

        for (c in reversed) {
            val added = c.toString().toInt() + more

            more = if (added > 9) 1 else 0
            sb.append((added % 10).toString())
        }

        if (more != 0) {
            sb.append(more.toString())
        }

        return sb.toString().reversed()
    }
}

fun main() {
    Solution1334.solution()
}