package 프로그래머스

class N1CardGame {
    fun solution(coin: Int, cards: IntArray): Int {
        val n = cards.size
        val target = n + 1
        var coin = coin
        val hand = HashSet<Int>()
        val pending = HashSet<Int>()

        for (i in 0 until n / 3) { hand.add(cards[i]) }

        var round = 1
        var next = n / 3

        while(next < n) {
            pending.add(cards[next])
            pending.add(cards[next + 1])
            next += 2

            val handPair = hand.firstOrNull { target - it in hand && isNotMe(target, it)}
            if (handPair != null) {
                hand.remove(handPair)
                hand.remove(target - handPair)
            } else {
                val handPendingPair = if (coin >= 1) hand.firstOrNull { target - it in pending && isNotMe(target, it) } else null
                if (handPendingPair != null) {
                    hand.remove(handPendingPair)
                    pending.remove(target - handPendingPair)
                    coin -= 1
                } else {
                    val pendingPair = if (coin >= 2) pending.firstOrNull { target - it in pending && isNotMe(target, it) } else null
                    if (pendingPair != null) {
                        pending.remove(pendingPair)
                        pending.remove(target - pendingPair)
                        coin -= 2
                    } else {
                        return round
                    }
                }
            }
            round++
        }
        return round
    }

    private fun isNotMe(target: Int, it: Int): Boolean = it != target - it
}