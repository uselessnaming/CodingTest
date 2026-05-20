package 프로그래머스

import java.util.LinkedList

class CaveExploration {
    private lateinit var graph: List<MutableList<Int>>
    private lateinit var visited: BooleanArray
    private val order: MutableMap<Int, Int> = mutableMapOf()
    private val waitingQueue: MutableMap<Int, MutableList<Int>> = mutableMapOf()

    fun solution(n: Int, path: Array<IntArray>, order: Array<IntArray>): Boolean {
        this.graph = List(n) { mutableListOf<Int>() }
        this.visited = BooleanArray(n) { false }

        // order map 설정
        setOrder(order)
        println("order : ${this.order}")

        // graph 설정 (양방향으로 두는 것이 좋을 것 같음
        setGraph(path)
        println(graph)

        if (this.order.containsKey(0)) return false
        // dfs로 탐색하되 visited를 개별적으로 두고 모두 visited했다면 answer을 true로 변경
        bfs()

        return visited.all { it }
    }

    private fun setOrder(order: Array<IntArray>) {
        for ((first, second) in order) {
            this.order[second] = first
        }
    }

    private fun setGraph(path: Array<IntArray>) {
        for ((x, y) in path) {
            graph[x].add(y)
            graph[y].add(x)
        }
    }

//    private fun dfs(start: Int) {
//        visited[start] = true
//
//        if (waitingQueue.containsKey(start)) {
//            val waits = waitingQueue.remove(start)!!
//            for (next in waits) {
//                dfs(next)
//            }
//        }
//
//        for (next in graph[start]) {
//            if (visited[next]) continue
//
//            if (order.containsKey(next) && !visited[order[next]!!]) {
//                waitingQueue.getOrPut(order[next]!!) { mutableListOf() }
//                    .add(next)
//
//                continue
//            }
//
//            dfs(next)
//        }
//    }

    private fun bfs() {
        val queue = ArrayDeque<Int>()
        queue.add(0)

        while(queue.isNotEmpty()) {
            val start = queue.removeFirst()

            if (waitingQueue.containsKey(start)) {
                val waits = waitingQueue.remove(start)!!
                for (next in waits) {
                    visited[next] = true
                    queue.add(next)
                }
            }

            for (next in graph[start]) {
                if (visited[next]) continue

                if (order.containsKey(next) && !visited[order[next]!!]) {
                    waitingQueue.getOrPut(order[next]!!) { mutableListOf() }
                        .add(next)

                    continue
                }

                visited[next] = true
                queue.add(next)
            }
        }
    }
}