package 백준

import java.util.*

// 백준 1826번 연료 채우기

class FillFuel {
    fun run(){
        // 마지막에 초기 연료 값, 마을의 위치가 나옴
        var cnt = 0
        val n = readln().toInt()
        val oils = mutableListOf<List<Int>>()
        repeat(n){ oils.add(readln().split(" ").map{it.toInt()}) }
        val (target, baseOil) = readln().split(" ").map{it.toInt()}
        oils.sortBy{it[0]}

        // PrioiryQueue는 기름 양으로 정렬
        val pq = PriorityQueue<Int>(compareByDescending<Int>{it})
        var cur = baseOil
        var idx = 0

        // 최대한으로 갈 수 있는 곳까지 올림
        while(idx < oils.size){
            if (cur >= target) {
                println(cnt)
                return
            }
            // limit보다 앞에 있는 곳들을 Priority Queue에 추가
            if (oils[idx][0] <= cur){ pq.offer(oils[idx][1]) }
            // pop(), 사용 가능한 기름 양만큼 추가 후 반복
            // pop() 마다 cnt +1
            else {
                if (pq.isNotEmpty()){
                    while(pq.isNotEmpty()) {
                        if (cur >= oils[idx][0]) break
                        cur += pq.poll()
                        cnt++
                    }
                } else {
                    println(-1)
                    return
                }
                idx--
            }
            idx++
        }
        while(pq.isNotEmpty()){
            if (cur >= target) {
                println(cnt)
                return
            }
            cur += pq.poll()
            cnt++
        }
        println(-1)
    }
}