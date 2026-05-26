package 프로그래머스

import kotlin.math.max

class TreeTrio {
    private lateinit var tree: List<MutableList<Int>>

    fun solution(n: Int, edges: Array<IntArray>): Int {
        tree = List(n + 1) { mutableListOf() }

        // tree 만들기
        for((start, end) in edges) {
            tree[start].add(end)
            tree[end].add(start)
        }

        val (A, _) = bfs(1)

        val (B, diameter) = bfs(A)

        val (_, countA) = bfsCount(A)
        val (_, countB) = bfsCount(B)

        return if (countA >= 2 || countB >= 2) diameter else diameter - 1
    }

    private fun bfs(start: Int): Pair<Int, Int> {
        val dist = IntArray(tree.size) { -1 }
        val q = ArrayDeque<Int>()
        q.add(start)
        dist[start] = 0

        var farNode = start
        var maxDist = 0

        while(q.isNotEmpty()) {
            val cur = q.removeFirst()
            for (next in tree[cur]) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1
                    q.add(next)
                    if (dist[next] > maxDist) {
                        maxDist = dist[next]
                        farNode = next
                    }
                }
            }
        }

        return Pair(farNode, maxDist)
    }

    private fun bfsCount(start: Int): Pair<Int, Int> {
        val dist = IntArray(tree.size) { -1 }
        val q = ArrayDeque<Int>()

        q.add(start)
        dist[start] = 0

        var maxDist = 0

        while(q.isNotEmpty()) {
            val cur = q.removeFirst()

            for(next in tree[cur]) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1
                    maxDist = max(maxDist, dist[next])
                    q.add(next)
                }
            }
        }

        var cnt = 0
        for (d in dist) {
            if (d == maxDist) cnt++
        }

        return maxDist to cnt
    }
}