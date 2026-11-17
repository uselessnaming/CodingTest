package 프로그래머스

class DropOneTwoThree {
    fun solution(edges: Array<IntArray>, target: IntArray): IntArray {
        val n = target.size
        val graph = Array<MutableList<Int>>(n + 1) { mutableListOf() }
        for ((parent, child) in edges) graph[parent].add(child)
        for (list in graph) list.sort()

        val need = IntArray(n + 1) { (target.getOrElse(it - 1) { 0 } + 2) / 3 }
        val leafCount = graph.count { it.isEmpty() } - 1

        val pointer = IntArray(n + 1)
        val visit = IntArray(n + 1)
        val order = ArrayList<Int>()
        var satisfied = (1..n).count { graph[it].isEmpty() && need[it] == 0 }

        do {
            var cur = 1
            while (graph[cur].isNotEmpty()) {
                val next = graph[cur][pointer[cur]]
                pointer[cur] = (pointer[cur] + 1) % graph[cur].size
                cur = next
            }
            visit[cur]++
            order.add(cur)
            if (visit[cur] > target[cur - 1]) return intArrayOf(-1)
            if (visit[cur] == need[cur]) satisfied++
        } while (satisfied < leafCount)

        val remain = target.copyOf()
        val left = visit.copyOf()
        val answer = IntArray(order.size)
        for ((i, leaf) in order.withIndex()) {
            left[leaf]--
            val r = left[leaf]
            var picked = 0
            for (v in 1..3) {
                val rest = remain[leaf - 1] - v
                if (rest in r..(3 * r)) {
                    picked = v
                    break
                }
            }

            if (picked == 0) return intArrayOf(-1)
            answer[i] = picked
            remain[leaf - 1] -= picked
        }
        return answer
    }
}