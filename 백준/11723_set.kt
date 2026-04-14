package 백준

import kotlin.text.toInt

class SetBj {
    private var bit = 0
    private val sb = StringBuilder()

    fun run () {
        val n = readln().toInt()

        repeat(n) {
            val line = readln().split(" ")

            val op = line[0]
            var num = 0

            if (line.size > 1) {
                num = line[1].toInt() - 1
            }

            when(op) {
                "add" -> bit = bit or (1 shl num)
                "remove" -> bit = bit and (1 shl num).inv()
                "check" -> check(num)
                "toggle" -> bit = bit xor (1 shl num)
                "all" -> bit = (1 shl 20) - 1
                "empty" -> bit = 0
            }
        }

        print(sb)
    }

    private fun check(num: Int) {
        if (bit and (1 shl num) != 0) sb.append("1\n")
        else sb.append("0\n")
    }
}