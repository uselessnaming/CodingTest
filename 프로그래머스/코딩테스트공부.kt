package 프로그래머스

class CodingTestStudy {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        return bfs(alp, cop, problems)
    }

    private fun bfs(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        val targetAlp = problems.maxOf { it[0] }
        val targetCop = problems.maxOf { it[1] }

        val startAlp = minOf(alp, targetAlp)
        val startCop = minOf(cop, targetCop)

        val dp = Array(targetAlp + 1) { IntArray(targetCop + 1) {Int.MAX_VALUE} }
        dp[startAlp][startCop] = 0

        for (a in startAlp..targetAlp) {
            for (c in startCop..targetCop) {
                val current = dp[a][c]
                if (current == Int.MAX_VALUE) continue

                if (a + 1 <= targetAlp) {
                    dp[a + 1][c] = minOf(dp[a + 1][c], current + 1)
                }

                if (c + 1 <= targetCop) {
                    dp[a][c+1] = minOf(dp[a][c+1], current + 1)
                }

                for (p in problems) {
                    val (alpReq, copReq, alpRwd, copRwd, cost) = p

                    if (a >= alpReq && c >= copReq) {
                        val nextAlp = minOf(targetAlp, a + alpRwd)
                        val nextCop = minOf(targetCop, c + copRwd)

                        dp[nextAlp][nextCop] = minOf(dp[nextAlp][nextCop], current + cost)
                    }
                }
            }

        }
        return dp[targetAlp][targetCop]
    }
}