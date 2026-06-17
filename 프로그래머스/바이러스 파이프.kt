package 프로그래머스

import java.util.BitSet
import java.util.LinkedList
import java.util.Queue

class VirusPipe {
    private val graph = mutableMapOf<Int, MutableList<Node>>()
    private val visitedState = HashSet<String>()
    private var answer = 1

    fun solution(n: Int, infection: Int, edges: Array<IntArray>, k: Int): Int {
        graphSetting(n, edges)

        val start = BitSet(n + 1)
        start[infection] = true

        dfs(0, start, n, k)

        return answer
    }

    private fun graphSetting(n: Int, edges: Array<IntArray>) {
        repeat(n) {
            graph[it + 1] = mutableListOf<Node>()
        }

        for ((start, end, type) in edges) {
            graph[start]!!.add(Node(end, type))
            graph[end]!!.add(Node(start, type))
        }
    }

    private fun dfs(depth: Int, infected: BitSet, n: Int, k: Int) {
        answer = maxOf(answer, infected.cardinality())

        if (depth == k) return

        val key = encode(infected, depth)

        if (!visitedState.add(key)) return

        for (type in 1..3) {
            val next = spread(n, infected, type)
            dfs(depth + 1, next, n, k)
        }
    }

    private fun encode(bitSet: BitSet, depth: Int): String {
        return bitSet.toLongArray().joinToString(",") + "|$depth"
    }

    private fun spread(n: Int, current: BitSet, pipeType: Int): BitSet {
        val infected = current.clone() as BitSet
        val queue: Queue<Int> = LinkedList()

        for (node in 1..n) {
            if (infected[node]) {
                queue.offer(node)
            }
        }

        while(queue.isNotEmpty()) {
            val now = queue.poll()

            for (next in graph[now]!!) {
                if (next.type != pipeType) continue

                if (!infected[next.link]) {
                    infected[next.link] = true
                    queue.offer(next.link)
                }
            }
        }

        return infected
    }

    data class Node(
        val link: Int,
        val type: Int
    )
}