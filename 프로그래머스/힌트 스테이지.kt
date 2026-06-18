package 프로그래머스

class HintStage {
    fun solution(cost: Array<IntArray>, hint: Array<IntArray>): Int {
        val n = cost.size
        val m = hint.size

        var answer = Int.MAX_VALUE

        for (mask in 0 until (1 shl m)) {
            val hintCount = IntArray(n)
            var totalCost = 0

            for (i in 0 until m) {
                if ((mask and (1 shl 1)) != 0) {
                    totalCost += hint[i][0]

                    for (j in 1 until hint[i].size) {
                        val stage = hint[i][j] - 1
                        hintCount[stage]++
                    }
                }
            }

            for (stage in 0 until n) {
                val usedHint = minOf(hintCount[stage], n - 1)
                totalCost += cost[stage][usedHint]
            }

            answer = minOf(answer, totalCost)
        }

        return answer
    }
}