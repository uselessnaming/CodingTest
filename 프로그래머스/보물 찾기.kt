package 프로그래머스

class FindTreasure {
    fun solution(depth: IntArray, money: Int, excavate: (Int) -> Int): Int {
        val choice = buildChoice(depth)

        var l = 0
        var r = depth.lastIndex

        while (l <= r) {
            val k = choice[l][r]

            when(excavate(k)) {
                0 -> return k
                -1 -> r = k - 1
                1 -> l = k + 1
            }
        }

        return -1
    }

    private fun buildChoice(depth: IntArray): Array<IntArray> {
        val n = depth.size
        val dp = Array(n) { IntArray(n) }
        val choice = Array(n) { IntArray(n) }

        for (i in 0 until n) {
            dp[i][i] = 0
            choice[i][i] = i
        }

        for (len in 2..n) {
            for (l in 0..n - len) {
                val r = l + len - 1

                var bestCost = Int.MAX_VALUE
                var bestK = l

                for (k in l..r) {
                    val left = if (k > l) dp[l][k - 1] else 0
                    val right = if (k < r) dp[k + 1][r] else 0

                    val cost = depth[k] + maxOf(left, right)

                    if (cost < bestCost) {
                        bestCost = cost
                        bestK = k
                    }
                }

                dp[l][r] = bestCost
                choice[l][r] = bestK
            }
        }
        return choice
    }
}