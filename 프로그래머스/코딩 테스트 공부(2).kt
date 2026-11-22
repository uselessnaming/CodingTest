package 프로그래머스

class CodingTestStudySecond {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        val targetAlp = problems.maxOf{it[0]}
        val targetCop = problems.maxOf{it[1]}
        val startAlp = minOf(alp, targetAlp)
        val startCop = minOf(cop, targetCop)

        val dp = Array (targetAlp + 1) { IntArray(targetCop + 1) { Int.MAX_VALUE } }
        dp[startAlp][startCop] = 0

        for (a in startAlp..targetAlp) {
            for (c in startCop..targetCop) {
                val cur = dp[a][c]

                if (cur == Int.MAX_VALUE) continue

                if (a + 1 <= targetAlp) {
                    dp[a + 1][c] = minOf(dp[a + 1][c], cur + 1)
                }

                if (c + 1 <= targetCop) {
                    dp[a][c + 1] = minOf(dp[a][c + 1], cur + 1)
                }

                for ((alpReq, copReq, alpRwd, copRwd, cost) in problems) {
                    if (a >= alpReq && c >= copReq) {
                        val nextAlp = minOf(a + alpRwd, targetAlp)
                        val nextCop = minOf(c + copRwd, targetCop)
                        dp[nextAlp][nextCop] = minOf(dp[nextAlp][nextCop], cur + cost)
                    }
                }
            }
        }

        return dp[targetAlp][targetCop]
    }
}