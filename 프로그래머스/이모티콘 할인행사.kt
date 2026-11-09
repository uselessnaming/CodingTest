package 프로그래머스

class EmoticonSaleEvent {
    private lateinit var users: Array<IntArray>
    private val answer = intArrayOf(-1, -1)

    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        this.users = users

        dfs(emoticons, IntArray(emoticons.size) { -1 }, 0)

        return answer
    }

    private fun dfs(emoticons: IntArray, sales: IntArray, idx: Int) {
        if (idx == sales.size) {
            var user = 0
            var total = 0

            for ((sale, limit) in users) {
                var sell = 0

                for (i in sales.indices) {
                    if (sales[i] >= sale) sell += ((emoticons[idx] * (100 - sales[i])) / 100)
                }

                total += if (sell >= limit) 0 else sell
                user += if (sell >= limit) 1 else 0
            }

            if ((user == answer[0] && total > answer[1]) || user > answer[0]) {
                answer[0] = user
                answer[1] = total
            }

            return
        }

        for (i in 0 until SALES_TYPE_SIZE) {
            sales[idx] = salePercent[i]
            dfs(emoticons, sales, idx + 1)
        }
    }

    companion object {
        const val SALES_TYPE_SIZE = 4
        val salePercent = intArrayOf(10, 20, 30, 40)
    }
}