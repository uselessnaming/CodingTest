package 프로그래머스

import java.util.PriorityQueue

class CountCreateServer {
    fun solution(players: IntArray, m: Int, k: Int): Int {
        val pq = PriorityQueue<List<Int>>(compareBy { it[0] })
        var size = 0
        var cnt = 0

        repeat(24) { time ->
            while(pq.isNotEmpty() && pq.peek()[0] == time) {
                size -= pq.poll()[1]
            }

            val need = players[time] / m
            var more = size - need
            if (more < 0) {
                more = -more
                size += more
                cnt += more
                pq.add(listOf(time + k, more))
            }
        }

        return cnt
    }
}