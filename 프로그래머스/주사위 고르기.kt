package 프로그래머스

class ChoiceDice {
    fun solution(dice: Array<IntArray>): IntArray {
        val total = dice.size
        val half = total / 2
        var bestWins = -1L
        var answer = intArrayOf()

        for (mask in 0 until (1 shl total)) {
            if (mask.countOneBits() != half) continue

            val aDice = ArrayList<IntArray>()
            val bDice = ArrayList<IntArray>()
            for (i in 0 until total) {
                if (mask and (1 shl i) != 0) aDice.add(dice[i]) else bDice.add(dice[i])
            }
            val cntA = distribute(aDice)
            val cntB = distribute(bDice)

            var less = 0L
            var wins = 0L
            for (s in 0 until MAX_SUM) {
                wins += cntA[s] * less
                less += cntB[s]
            }

            if (wins > bestWins) {
                bestWins = wins
                answer = IntArray(half)
                var idx = 0
                for (i in 0 until total) if (mask and (1 shl i) != 0) answer[idx++] = i + 1
            }
        }

        return answer
    }

    private fun distribute(list: List<IntArray>): LongArray {
        var dp = LongArray(MAX_SUM)
        dp[0] = 1
        for (dice in list) {
            val next = LongArray(MAX_SUM)
            for (s in 0 until MAX_SUM) {
                if (dp[s] == 0L) continue
                for (face in dice) next[s + face] += dp[s]
            }
            dp = next
        }

        return dp
    }

    companion object {
        const val MAX_SUM = 501
    }
}