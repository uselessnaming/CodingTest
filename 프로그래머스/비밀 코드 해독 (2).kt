package 프로그래머스

class DecodingSecond {
    private var combs = mutableSetOf<List<Int>>()
    private val LIMIT = 5

    fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int{
        var answer = 0
        makeCombs(0, 1, n, emptyList())

        for(comb in combs) {
            if (isAvailable(comb, q, ans)) answer++
        }

        return answer
    }

    private fun makeCombs(idx: Int, last: Int, n: Int, current: List<Int>) {
        if (idx == LIMIT) {
            combs.add(current.sorted())
            return
        }

        for(i in last..n) {
            if (i in current) continue

            makeCombs(idx + 1, i + 1, n, current + i)
        }
    }

    private fun isAvailable(comb: List<Int>, q: Array<IntArray>, ans: IntArray): Boolean {
        for(idx in q.indices) {
            if (comb.count{ it in q[idx] } != ans[idx]) return false
        }

        return true
    }
}