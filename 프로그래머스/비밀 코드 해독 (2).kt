package 프로그래머스

class DecodingSecond {
    private lateinit var q: Array<IntArray>
    private lateinit var ans: IntArray
    private var answer = 0
    private val LIMIT = 5

    fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
        this.q = q
        this.ans = ans
        val visited =  BooleanArray(n + 1) { false }.apply { this[0] = true }
        backTracking(0, 1, n, emptyList(), visited)

        return answer
    }

    private fun backTracking(idx: Int, last: Int, n: Int, current: List<Int>, visited: BooleanArray) {
        if (idx == LIMIT) {
            if (isAvailable(current)) answer++
            return
        }

        for (i in last..n) {
            if (visited[i]) continue

            visited[i] = true
            backTracking(idx + 1, i + 1, n, current + i, visited)
            visited[i] = false
        }
    }

    private fun isAvailable(comb: List<Int>): Boolean {
        for (idx in q.indices) {
            if (comb.count { it in q[idx] } != ans[idx]) return false
        }

        return true
    }
}