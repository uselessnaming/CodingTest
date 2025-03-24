package 백준

import java.util.*

// 백준 1781번 컵라면

class Ramen {
    fun run(){
        val n = readln().toInt()
        val ramens = mutableListOf<List<Int>>()
        // 입력 받기
        repeat(n){ ramens.add(readln().split(" ").map{it.toInt()}) }

        // 가장 작은 deadline 순으로 정렬
        ramens.sortWith(compareBy<List<Int>>({it[0]}).thenByDescending { it[1] })

        // ramen for문
        val priorityQueue = PriorityQueue<Int>()
        for(ramen in ramens){
            if (priorityQueue.size < ramen[0]) { priorityQueue.add(ramen[1]) }
            else {
                if (priorityQueue.peek() < ramen[1]){
                    priorityQueue.poll()
                    priorityQueue.add(ramen[1])
                }
            }
        }
        var answer = 0
        while(priorityQueue.isNotEmpty()){ answer += priorityQueue.poll() }
        println(answer)
    }
}