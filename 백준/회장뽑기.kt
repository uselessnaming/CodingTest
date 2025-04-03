package 백준

import java.util.ArrayDeque
import kotlin.math.max
import kotlin.time.measureTime

// 백준 2660번 회장뽑기

class ElectRepresentative{
    val candidates = mutableMapOf<Int, MutableList<Int>>()
    val graph = mutableMapOf<Int, MutableList<Int>>()
    fun run(){
        // 입력
        val n = readln().toInt()
        for(i in 1..n){graph[i] = mutableListOf()}
        println(graph)
        while(true){
            val (start, end) = readln().split(" ").map{it.toInt()}
            if (start == -1 || end == -1) break
            graph[start]!!.add(end)
            graph[end]!!.add(start)
        }

        // bfs
        // n명까지 for문
        val times = measureTime {
            for(idx in 1..n){
                // for문 속에서 bfs
                bfs(n, idx)
            }
        }
        println(times)
        // 시작점에서 graph로 연결된 사람들을 확인 visited 처리
        // 만약 graph[idx]의 리스트가 비어있지 않다면 queue에 cnt+1과 그 사람을 추가
        // 한 사람이 끝날 때마다 점수에 해당하는 후보들을 list로 map에 추가
        // 회장 후보
        println(graph)
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