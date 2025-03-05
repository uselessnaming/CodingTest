package 프로그래머스

import java.util.*

class AddServer {
    fun solution(players: IntArray, m: Int, k: Int): Int{
        val pq = PriorityQueue <List<Int>>(compareBy{it[0]})
        var size = 0
        var count = 0

        for(i in 0..23){
            println(pq)
            while(pq.isNotEmpty() && pq.peek()[0] == i){
                size -= pq.poll()[1]
            }
            val need = players[i] / m
            var more = size - need
            println("i : $i / $need / $more")
            if (more < 0){
                more = -more
                size += more
                count += more
                pq.add(listOf(i+k, more))
            }
        }

        return count
    }
}