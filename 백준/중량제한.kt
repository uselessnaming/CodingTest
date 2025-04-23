package 백준

// 백준 1939번
// 알고리즘 생각은 잘했는데 조건 중 다음 edge가 연결되었지만 cost가 더 커서 가지 못하는 경우를 생각해야 하므로
// if i.vertex == end 을 먼저 확인하면 안됨
// i.weight가 >= cost인지 확인한 후 end를 체크하고 end라면 return
// 아니라면 queue에 추가하는 것으로 해야 함

import kotlin.math.max

class WeightLimit{
    val graph = mutableMapOf<Int, MutableList<Edge>>()
    fun run(){
        val (n, m) = readln().split(" ").map{ it.toInt() }
        for(i in 0..n){ graph[i] = mutableListOf() }

        var left = 1L
        var right = 0L

        repeat(m){
            readln().split(" ").map{it.toInt()}.apply{
                right = max(this[2].toLong(), right)
                graph[this[0]]!!.add(Edge(this[1], this[2].toLong()))
                graph[this[1]]!!.add(Edge(this[0], this[2].toLong()))
            }
        }

        val (start, end) = readln().split(" ").map{ it.toInt() }

        var ans = 0L
        while(left <= right){
            val mid = (left + right) / 2
            if (canFinish(start, end, mid)){
                ans = max(ans, mid)
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        println(ans)
    }

    fun canFinish(start: Int, end: Int, cost: Long): Boolean{
        val visited = BooleanArray(graph.size + 1){ false }
        visited[start] = true
        val queue = ArrayDeque<Int>()
        queue.addLast(start)

        while(queue.isNotEmpty()){
            val cur = queue.removeFirst()
            for(i in graph[cur]!!){
                if (i.weight >= cost){
                    if (i.vertex == end){ return true }
                    if (!visited[i.vertex]){
                        visited[i.vertex] = true
                        queue.addLast(i.vertex)
                    }
                }
            }
        }
        return false
    }
}

data class Edge(val vertex: Int, val weight: Long)