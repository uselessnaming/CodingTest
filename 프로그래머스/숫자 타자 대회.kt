package 프로그래머스

class TypingNumberCompetition {
    private val dp = Array(100000) { Array(10) { IntArray(10) { Int.MAX_VALUE } } }
    private val weight = arrayOf(
        intArrayOf(1, 7, 6, 7, 5, 4, 5, 3, 2, 3),
        intArrayOf(7, 1, 2, 4, 2, 3, 5, 4, 5, 6),
        intArrayOf(6, 2, 1, 2, 3, 2, 3, 5, 4, 5),
        intArrayOf(7, 4, 2, 1, 5, 3, 2, 6, 5, 4),
        intArrayOf(5, 2, 3, 5, 1, 2, 4, 2, 3, 5),
        intArrayOf(4, 3, 2, 3, 2, 1, 2, 3, 2, 3),
        intArrayOf(5, 5, 3, 2, 4, 2, 1, 5, 3, 2),
        intArrayOf(3, 4, 5, 6, 2, 3, 5, 1, 2, 4),
        intArrayOf(2, 5, 4, 5, 3, 2, 3, 2, 1, 2),
        intArrayOf(3, 6, 5, 4, 5, 3, 2, 4, 2, 1),
    )

    fun solution(numbers: String): Int {
        return solve(numbers, 0, 4, 6)
    }

    private fun solve(numbers: String ,idx: Int, l: Int, r: Int): Int {
        if (idx >= numbers.length) return 0
        if (dp[idx][l][r] != Int.MAX_VALUE) return dp[idx][l][r]
        val next = numbers[idx] - '0'
        if (next != r) {
            dp[idx][l][r] = dp[idx][l][r].coerceAtMost(solve(numbers, idx + 1, next, r) + weight[l][next])
        }
        if (next != l) {
            dp[idx][l][r] = dp[idx][l][r].coerceAtMost(solve(numbers, idx + 1, l, next) + weight[r][next])
        }

        return dp[idx][l][r]
    }
}