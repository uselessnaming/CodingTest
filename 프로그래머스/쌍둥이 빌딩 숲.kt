package 프로그래머스

class TwinBuildingForest {
    private val INF = 1_000_000_007L
    private lateinit var dp: Array<LongArray>

    fun solution(n: Int, count: Int): Int {
        dp = Array(n + 1) { LongArray(n + 1) { 0L } }

        dp[1][1] = 1

        for (i in 2..n) {
            for (j in 1..i) {
                dp[i][j] = (dp[i - 1][j - 1] + (dp[i - 1][j] * 2L * (i - 1))) % INF
            }
        }

        return dp[n][count].toInt()
    }
}