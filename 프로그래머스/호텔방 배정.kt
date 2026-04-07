package 프로그래머스

/** union-find 자료 구조 */
/** 시간 초과로 접근 실패 */

class HotelScheduling {
    private lateinit var parents: MutableMap<Long, Long>

    fun solution(k: Long, roomNumber: LongArray): LongArray {
        val answer = mutableListOf<Long>()

        parents = mutableMapOf()
        for (room in roomNumber) {
            parents[room] = room
        }

        for (room in roomNumber) {
            if (parents[room] == room) {
                answer.add(room)
                union(room, room + 1)
            } else {
                val root = find(room)
                union(root, root + 1)
                answer.add(root)
            }
        }

        return answer.toLongArray()
    }

    private fun find(target: Long): Long {
        if (parents[target] != target) {
            parents[target] = find(parents[target]!!)
        }

        return parents[target]!!
    }

    private fun union(a: Long, b: Long) {
        if (!parents.containsKey(b)) {
            parents[b] = b
        }

        val rootA = find(a)
        val rootB = find(b)
        parents[rootA] = rootB
    }
}

// 올바른 방 번호를 먼저 배정한다
// +1 한 값으로 진행한다
// +2 한 값으로 진행한다 이런느낌