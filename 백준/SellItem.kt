package 백준

import java.util.*
import java.util.Collections.max
import java.util.Collections.min

// 1487번 물건 팔기

class SellItem {
    val infos = mutableListOf<List<Int>>()
    fun run(){
        val n = readln().toInt()
        repeat(n){ infos.add(readln().split(" ").map{it.toInt()}) }

        val start = min(infos.map{it[0]})
        val end = max(infos.map{it[0]})

        // pq first = 이득, 설정 가격
        val pq = PriorityQueue<Pair<Int, Int>>(compareByDescending<Pair<Int, Int>>{it.first}.thenBy{it.second})

        for(price in start..end){
            var totalPrice = 0
            for(info in infos){
                if(price > info[0]) continue
                val value = price - info[1]
                if (value > 0) totalPrice += value
            }
            if (totalPrice > 0) { pq.add(Pair(totalPrice, price)) }
        }
        val result = if(pq.isNotEmpty()) pq.poll().second else 0
        println(result)
    }
}