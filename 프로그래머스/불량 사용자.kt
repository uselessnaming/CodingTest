package 프로그래머스

class IllegalUser {
    private lateinit var visited: BooleanArray
    private var answerSet = mutableSetOf<Set<String>>()

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        visited = BooleanArray(user_id.size) { false }
        makeComb(user_id, banned_id, arrayOf())

        return answerSet.size
    }

    private fun makeComb(userIds: Array<String>, bannedIds: Array<String>, users: Array<String>) {
        if (users.size == bannedIds.size) {
            if (isMatch(users, bannedIds)) {
                answerSet.add(users.toSet())
            }
            return
        }

        for (idx in userIds.indices) {
            if (visited[idx]) continue

            visited[idx] = true
            makeComb(userIds, bannedIds, users + userIds[idx])
            visited[idx] = false
        }
    }

    private fun isMatch(users: Array<String>, bannedIds: Array<String>): Boolean {
        users.forEachIndexed { idx, user ->
            if (user.length != bannedIds[idx].length) return false

            val reg = Regex(bannedIds[idx].replace('*', '.'))

            if (!reg.matches(user)) return false
        }

        return true
    }
}