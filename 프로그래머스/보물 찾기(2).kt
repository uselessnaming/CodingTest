package 프로그래머스

// 구간 당 최소 값을 구하는 dp를 미리 생성
// 반복하면서 -1이나 1일 경우 범위 변경, 0일 경우 답 return

class FindTreasureSecond {
    fun solution(depth: IntArray, money: Int, excavate: (Int) -> Int): Int {
        val choice = makeChoice(depth)
        var start = 0
        var end = depth.lastIndex

        while(start <= end) {
            val k = choice[start][end]

            when(excavate(k + 1)) {
                0 -> return k + 1
                -1 -> end = k - 1
                1 -> start = k + 1
            }
        }

        return -1
    }

    fun makeChoice(depth: IntArray): Array<IntArray> {
        val n = depth.size

        val dp = Array(n) { IntArray(n) }
        val choice = Array(n) { IntArray(n) }

        for (i in 0 until n) {
            dp[i][i] = depth[i]
            choice[i][i] = i
        }

        for (len in 2..n) {
            for (start in 0..n - len) {
                val end = start + len - 1

                var bestCost = Int.MAX_VALUE
                var bestK = start

                for (k in start..end) {
                    val left = if (k > start) dp[start][k - 1] else 0
                    val right = if (k < end) dp[k + 1][end] else 0
                    val cost = depth[k] + maxOf(left, right)

                    if (cost < bestCost) {
                        bestCost = cost
                        bestK = k
                    }
                }

                dp[start][end] = bestCost
                choice[start][end] = bestK
            }
        }

        return choice
    }
}