package 프로그래머스

class DecodingSecond {
    private lateinit var qMask: IntArray
    private lateinit var ans: IntArray
    private var answer = 0
    private val LIMIT = 5

    fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
        this.ans = ans
        qMask = IntArray(q.size) { i -> q[i].fold(0) { mask, num -> mask or (1 shl num) } }

        backTracking(0, 1, n, 0)
        return answer
    }

    private fun backTracking(idx: Int, last: Int, n: Int, current: Int) {
        if (idx == LIMIT) {
            if (isAvailable(current)) answer++
            return
        }

        for (i in last..n) {
            backTracking(idx + 1, i + 1, n, current or (1 shl i))
        }
    }

    private fun isAvailable(comb: Int): Boolean {
        for (idx in qMask.indices) {
            if ((comb and qMask[idx]).countOneBits() != ans[idx]) return false
        }

        return true
    }
}