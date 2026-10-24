package 프로그래머스

class MostGiveGift {
    private lateinit var friends: Array<String>

    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        this.friends = friends
        val gift = Array(friends.size) { IntArray(friends.size) { 0 } }

        for (info in gifts) {
            val (sender, receiver) = info.split(" ")
            gift[sender.getIdx()][receiver.getIdx()]++
        }

        val giftScore = IntArray(friends.size) { 0 }

        for (idx in friends.indices) {
            val sendScore = gift[idx].sum()
            val receiveScore = gift.sumOf { it[idx] }
            giftScore[idx] = sendScore - receiveScore
        }

        val receivers = IntArray(friends.size) { 0 }
        for (i in friends.indices) {
            for (j in friends.indices) {
                if (i == j) continue

                if (gift[i][j] == gift[j][i]) {
                    if (giftScore[i] > giftScore[j]) {
                        receivers[i]++
                    }
                } else if (gift[i][j] > gift[j][i]) {
                    receivers[i]++
                }
            }
        }

        return receivers.max()
    }

    private fun String.getIdx(): Int {
        return friends.indexOf(this)
    }
}