package 프로그래머스

import java.util.PriorityQueue

class EscapeMaze {
    fun solution(n: Int, start: Int, end: Int, roads: Array<IntArray>, traps: IntArray): Int {
        val trapIdx = mutableMapOf<Int, Int>()

        traps.forEachIndexed { idx, trap -> trapIdx[trap] = idx }

        val graph = Array(n + 1) { mutableListOf<Edge>() }

        for ((p, q, s) in roads) {
            graph[p].add(Edge(p, q, s, false))
            graph[q].add(Edge(q, p, s, true))
        }

        val dist = Array(n + 1) { IntArray(1 shl traps.size) { Int.MAX_VALUE } }
        val pq = PriorityQueue<Node>(compareBy { it.cost })

        dist[start][0] = 0
        pq.add(Node(start, 0, 0))

        while (pq.isNotEmpty()) {
            val (cur, state, cost) = pq.poll()

            if (cur == end) return cost
            if (dist[cur][state] < cost) continue

            for (edge in graph[cur]) {
                val curActive = isActive(cur, state, trapIdx)
                val nextActive = isActive(edge.to, state, trapIdx)
                val reversed = curActive xor nextActive
                val canMove = if (!reversed) !edge.reversed else edge.reversed

                if (!canMove) continue

                var nextState = state

                if (trapIdx.containsKey(edge.to)) {
                    val idx = trapIdx[edge.to]!!
                    nextState = state xor (1 shl idx)
                }

                val nextCost = cost + edge.cost

                if (dist[edge.to][nextState] > nextCost) {
                    dist[edge.to][nextState] = nextCost
                    pq.add(Node(edge.to, nextState, nextCost))
                }
            }
        }

        return -1
    }

    private fun isActive(node: Int, state: Int, trapIdx: Map<Int, Int>): Boolean {
        val idx = trapIdx[node] ?: return false
        return (state and (1 shl idx)) != 0
    }

    data class Edge(
        val from: Int,
        val to: Int,
        val cost: Int,
        val reversed: Boolean
    )

    data class Node(
        val vertex: Int,
        val state: Int,
        val cost: Int
    )
}