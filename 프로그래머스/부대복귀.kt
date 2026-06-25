package 프로그래머스

class ReturnArmy {
    private lateinit var graph: List<MutableList<Int>>

    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        setGraph(n, roads)
        return sources.map {
            getDist(n, it, destination)
        }.toIntArray()
    }

    private fun setGraph(n: Int, roads: Array<IntArray>) {
        graph = List(n + 1) { mutableListOf() }
        for ((start, end) in roads) {
            graph[start].add(end)
            graph[end].add(start)
        }
    }

    private fun getDist(n: Int, start: Int, end: Int): Int {
        if (start == end) return 0

        val visited = BooleanArray(n + 1) { false }
        val queue = ArrayDeque<List<Int>>()

        queue.add(listOf(start, 0))
        visited[start] = true

        while(queue.isNotEmpty()) {
            val (cur, cnt) = queue.removeFirst()
            val next = graph[cur]

            for (node in next) {
                if (visited[node]) continue

                if (node == end) return cnt + 1

                visited[node] = true
                queue.add(listOf(node, cnt + 1))
            }
        }

        return -1
    }
}