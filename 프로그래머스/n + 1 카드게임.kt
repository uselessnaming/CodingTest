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

            val zero = hand.firstOrNull { target - it in hand }
            if (zero != null) {
                hand.remove(zero)
                hand.remove(target - zero)
            } else {
                val one = if (coin >= 1) hand.firstOrNull { target - it in pending } else null
                if (one != null) {
                    hand.remove(one)
                    pending.remove(target - one)
                    coin -= 1
                } else {
                    val two = if (coin >= 2) pending.firstOrNull { target - it in pending } else null
                    if (two != null) {
                        pending.remove(two)
                        pending.remove(target - two)
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
}