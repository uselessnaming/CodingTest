package 프로그래머스

class N1CardGameSecond {
    fun solution(coin: Int, cards: IntArray): Int {
        val n = cards.size
        val target = n + 1
        var coin = coin
        val inHand = BooleanArray(n + 1)
        val seen = BooleanArray(n + 1)

        for (i in 0 until n / 3) {
            inHand[cards[i]] = true
        }

        var handPair = 0
        for (i in 0 until n / 3) {
            if (inHand[target - cards[i]]) handPair++
        }

        handPair /= 2
        var handPendingPair = 0
        var pendingPair = 0

        var round = 1
        var next = n / 3

        while (next < n) {
            for (j in 0..1) {
                val c = cards[next + j]
                if (inHand[target - c]) handPendingPair++
                else if (seen[target - c]) pendingPair++
                seen[c] = true
            }
            next += 2

            if (handPair > 0) {
                handPair--
            } else if (coin >= 1 && handPendingPair > 0) {
                handPendingPair--
                coin -= 1
            } else if (coin >= 2 && pendingPair > 0) {
                pendingPair--
                coin -= 2
            } else {
                return round
            }

            round++
        }
        return round
    }
}