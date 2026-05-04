package 프로그래머스

class Multiplication {
    private lateinit var table: IntArray

    fun solution(e: Int, starts: IntArray): IntArray {
        table = IntArray(e + 1)

        getDivisor(e)

        val answer = IntArray(e + 1)
        var maxIdx = e

        for (i in e downTo 1) {
            if (table[i] >= table[maxIdx]) {
                maxIdx = i
            }
            answer[i] = maxIdx
        }

        return starts.map { answer[it] }.toIntArray()
    }

    private fun getDivisor(end: Int) {
        for (start in 1..end) {
            for (num in start..end step start) {
                table[num]++
            }
        }
    }
}