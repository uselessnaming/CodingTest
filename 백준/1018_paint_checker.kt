package 백준

import kotlin.math.min

class PaintChecker() {
    private var checker = mutableListOf<String>()
    private var answer = 64

    fun run() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        initChecker(column = n)
        getAnswer()
        println(answer)
    }

    private fun initChecker(column: Int) {
        repeat(times = column) {
            val data = readln().trim()
            checker.add(data)
        }
    }

    private fun getAnswer() {
        for (i in 0..checker.size - 8) {
            for (j in 0..checker[0].length - 8) {
                val tempChecker = checker.subList(i, i + 8).map { it.substring(j, j + 8) }
                findChecker(tempChecker)
            }
        }
    }

    private fun findChecker(checker: List<String>) {
        val bScore = getBScore(checker = checker)
        val wScore = getWScore(checker = checker)

        val score = min(bScore, wScore)

        if (score < answer) {
            answer = score
        }
    }

    private fun getBScore(checker: List<String>): Int {
        var score = 0

        checker.forEachIndexed { i, row ->
            row.forEachIndexed { j, col ->
                if ((i + j) % 2 == 0) {
                    if (col != 'B') score++
                } else {
                    if (col == 'B') score++
                }
            }
        }

        return score
    }

    private fun getWScore(checker: List<String>): Int {
        var score = 0

        checker.forEachIndexed { i, row ->
            row.forEachIndexed { j, col ->
                if ((i + j) % 2 == 0) {
                    if (col != 'W') score++
                } else {
                    if (col == 'W') score++
                }
            }
        }

        return score
    }
}