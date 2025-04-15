package 백준

import kotlin.math.max

class Test{
    val candidates = mutableMapOf<Int, MutableList<Int>>()
    val graph = mutableMapOf<Int, MutableList<Int>>()
    fun run(){
        // 입력
        val n = readln().toInt()
        for(i in 1..n){graph[i] = mutableListOf()}
        while(true){
            val (start, end) = readln().split(" ").map{it.toInt()}
            if (start == -1 || end == -1) break
            graph[start]!!.add(end)
            graph[end]!!.add(start)
        }

        // bfs
        // n명까지 for문
        for(idx in 1..n){
            // for문 속에서 bfs
            bfs(n, idx)
        }

        // 출력
        val minScore = candidates.keys.min()
        println("$minScore ${candidates[minScore]!!.size}")
        println(candidates[minScore]!!.map{it.toString()}.joinToString(" "))
    }

    fun bfs(n: Int, idx: Int){
        var res = 0
        val visited = BooleanArray(n+1){false}
        val queue = ArrayDeque<List<Int>>()
        visited[idx] = true
        queue.addFirst(listOf(idx, 0))
        while(queue.isNotEmpty()){
            val (cur, cnt) = queue.removeFirst()
            res = max(res, cnt)
            for(node in graph[cur]!!){
                if(!visited[node]){
                    visited[node] = true
                    queue.add(listOf(node, cnt+1))
                }
            }
        }
        if(candidates.contains(res)){ candidates[res]!!.add(idx) }
        else { candidates[res] = mutableListOf(idx) }
    }
}