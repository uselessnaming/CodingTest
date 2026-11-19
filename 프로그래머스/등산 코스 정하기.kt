package 프로그래머스

import java.util.*

class SelectCourse {
    private lateinit var graph: Array<ArrayList<Node>>

    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        initGraph(n, paths, gates, summits)

        val res = dijkstra(gates)
        val answer = intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE)

        for (summit in summits.sorted()) {
            if (res[summit] < answer[1]) {
                answer[0] = summit
                answer[1] = res[summit]
            }
        }

        return answer
    }

    private fun initGraph(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray) {
        val isGate = BooleanArray(n + 1)
        for (g in gates) isGate[g] = true
        val isSummit = BooleanArray(n + 1)
        for (s in summits) isSummit[s] = true

        graph = Array(n + 1) { ArrayList<Node>() }
        for ((i, j, w) in paths) {
            if (!isSummit[i] && !isGate[j]) graph[i].add(Node(j, w))
            if (!isSummit[j] && !isGate[i]) graph[j].add(Node(i, w))
        }
    }

    private fun dijkstra(gates: IntArray): IntArray {
        val costs = IntArray(graph.size) { Int.MAX_VALUE }
        val queue = PriorityQueue<Node>(compareBy{it.intensity})

        for (gate in gates) {
            queue.add(Node(gate, 0))
            costs[gate] = 0
        }

        while(queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.intensity < costs[cur.idx]) {
                costs[cur.idx] = cur.intensity
            }

            for (next in graph[cur.idx]) {
                val nextIntensity = maxOf(cur.intensity, next.intensity)
                if (nextIntensity < costs[next.idx]) {
                    costs[next.idx] = nextIntensity
                    queue.add(Node(next.idx, nextIntensity))
                }
            }
        }

        return costs
    }

    data class Node(
        val idx: Int,
        val intensity: Int
    ): Comparable<Node> {
        override fun compareTo(other: Node): Int = intensity - other.intensity
    }
}