package 백준

import kotlin.math.min

class Switch {
    lateinit var switches: List<Int>
    fun run() {
        val n = readln().trim().toInt()
        switches = readln().trim().split(" ").map { it.toInt() }

        val students = readln().trim().toInt()
        repeat(students) {
            val (gen, idx) = readln().trim().split(" ")
            val switchIdx = idx.toInt()
            val gender = gen.toInt()

            if (gender == 1) {
                maleUpdate(switchIdx)
            } else {
                femaleUpdate(switchIdx)
            }
        }
        printSwitches()
    }

    private fun maleUpdate(idx: Int) {
        switches = switches.mapIndexed { i, value ->
            if ((i + 1) % idx == 0) {
                if (value == 0) 1 else 0
            } else {
                value
            }
        }
    }

    private fun femaleUpdate(idx: Int) {
        val idx = idx - 1
        val limit = min(idx, switches.size - idx - 1)
        var range = 0

        for (i in 1..limit) {
            if (switches[idx - i] == switches[idx + i]) {
                range += 1
            } else {
                break
            }
        }

        switches = switches.mapIndexed { i, value ->
            if (i in idx - range..idx + range) {
                if (value == 0) 1 else 0
            } else {
                value
            }
        }
    }

    private fun printSwitches() {
        switches.chunked(20).map { subSwitches ->
            println(subSwitches.joinToString(" "))
        }
    }
}