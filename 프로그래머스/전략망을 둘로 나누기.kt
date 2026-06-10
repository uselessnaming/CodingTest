package 프로그래머스

import kotlin.math.abs

class DivideElectricNet {
    private lateinit var tree: List<MutableList<Int>>
    fun solution(n: Int, wires: Array<IntArray>): Int {
        var answer: Int = Int.MAX_VALUE
        tree = List(n + 1) { mutableListOf() }

        makeTree(wires)

        for (wire in wires) {
            val score = getAnswer(n, wire)
            answer = minOf(answer, score)
            println("cur wire : ${wire.toList()}")
            println("score: $score")
            println("answer fetch : $answer")
        }

        return answer
    }

    private fun makeTree(wires: Array<IntArray>) {
        for ((start, end) in wires) {
            tree[start].add(end)
            tree[end].add(start)
        }
    }

    private fun getAnswer(n: Int, wire: IntArray): Int {
        val visited = BooleanArray(n + 1) { false }.apply { this[0] = true }

        val nodeSet1 = cntNode(1, wire, visited)

        var nodeSet2 = 0

        for (i in 1 until n) {
            if (visited[i]) continue

            nodeSet2 = cntNode(i, wire, visited)
        }

        return abs(nodeSet2 - nodeSet1)
    }

    private fun cntNode(startNode: Int, disconnected: IntArray, visited: BooleanArray): Int {
        val queue = ArrayDeque<Int>()
        queue.add(startNode)
        visited[startNode] = true

        var cnt = 0

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()

            val next = tree[cur]

            for (node in next) {
                if (visited[node]) continue
                if (cur == disconnected[0] && node == disconnected[1] || cur == disconnected[1] && node == disconnected[0]) continue

                visited[node] = true
                queue.add(node)
                cnt++
            }
        }

        return cnt
    }
}