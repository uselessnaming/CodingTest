package 프로그래머스

class PerfectCrimeWithAI {
    fun solution(info: Array<IntArray>, n: Int, m: Int): Int {
        val INF = Int.MAX_VALUE / 2
        var dp = IntArray(n) { INF }
        dp[0] = 0

        for ((ta, tb) in info.map { it[0] to it[1] }) {
            val next = IntArray(n) { INF }
            for (a in 0 until n) {
                if (dp[a] == INF) continue
                if (a + ta < n) next[a + ta] = minOf(next[a + ta], dp[a])
                next[a] = minOf(next[a], dp[a] + tb)
            }
            dp = next
        }
        return (0 until n).firstOrNull { dp[it] < m } ?: -1
    }
}