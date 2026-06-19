class InvalidUser {
    private lateinit var visit: Array<Boolean>
    private var answerSet = mutableSetOf<Set<String>>()
    var limit = 0

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        limit = user_id.size
        visit = Array(limit) { false }
        makeCombination(user_id, banned_id, arrayOf())
        return answerSet.size
    }

    fun makeCombination(userIds: Array<String>, bannedId: Array<String>, users: Array<String>) {
        if (users.size == bannedId.size) {
            if (matchId(users, bannedId)) answerSet.add(users.toSet())
            return
        }

        for (i in userIds.indices) {
            if (visit[i]) continue
            visit[i] = true
            makeCombination(userIds, bannedId, users + userIds[i])
            visit[i] = false
        }
    }

    private fun matchId(userIds: Array<String>, bannedIds: Array<String>): Boolean {
        for (i in bannedIds.indices) {
            if (userIds[i].length != bannedIds[i].length) return false

            val reg = Regex(bannedIds[i].replace('*', '.'))

            if (!reg.matches(userIds[i])) return false
        }

        return true
    }
}