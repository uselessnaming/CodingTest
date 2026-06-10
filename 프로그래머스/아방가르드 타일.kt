package 프로그래머스

class AvantGarde {
    private val MOD = 1_000_000_007L

    fun solution(n: Int): Int {
        when(n) {
            1 -> return 1
            2 -> return 3
            3 -> return 10
            4 -> return 23
            5 -> return 62
        }

        val dp = LongArray(n + 1).apply {
            this[0] = 1
            this[1] = 1
            this[2] = 3
            this[3] = 10
            this[4] = 23
            this[5] = 62
        }

        for (i in 6..n) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2] + 6 * dp[i - 3] + dp[i - 4] - dp[i - 6]) % MOD

            if (dp[i] <0) dp[i] += MOD
        }

        return dp[n].toInt()
    }
}