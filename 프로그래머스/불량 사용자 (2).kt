package 프로그래머스

class IllegalUserSecond {
    private lateinit var visited: IntArray
    private val answerSet = mutableSetOf<Int>()

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val n = user_id.size
        val m = banned_id.size
        visited = IntArray(m)

        for (i in 0 until m) {
            var mask = 0
            for (j in 0 until n) {
                if (matches(user_id[j], banned_id[i])) {
                    mask = mask or (1 shl j)
                }
            }
            visited[i] = mask
        }

        dfs(0, 0, banned_id.size)
        return answerSet.size
    }

    private fun matches(user: String, banned: String): Boolean {
        if (user.length != banned.length) return false

        val reg = Regex(banned.replace('*', '.'))
        return reg.matches(user)
    }

    private fun dfs(idx: Int, used: Int, m: Int) {
        if (idx == m) {
            answerSet.add(used)
            return
        }

        var available = visited[idx] and used.inv()

        while(available != 0) {
            val pos = available and (-available)
            available -= pos

            dfs(idx + 1, used or pos, m)
        }
    }
}