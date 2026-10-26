package 프로그래머스

class OddEvenTree {
    private val graph = mutableMapOf<Int, MutableList<Int>>()

    fun solution(nodes: IntArray, edges: Array<IntArray>): IntArray {
        val answer = intArrayOf(0, 0)

        for (node in nodes) {
            graph[node] = mutableListOf()
        }

        for ((start, end) in edges) {
            graph[start]!!.add(end)
            graph[end]!!.add(start)
        }

        val visited = mutableSetOf<Int>()

        for (node in nodes) {
            if (visited.contains(node)) continue

            val treeFinder = TreeFinder()
            findTrees(visited, treeFinder, node)
            answer[0] += treeFinder.getOddEvenTree()
            answer[1] += treeFinder.getReverseOddEvenTree()
        }

        return answer
    }

    fun findTrees(visited: MutableSet<Int>, treeFinder: TreeFinder, current: Int) {
        val next = graph[current]!!

        when {
            current % 2 == next.size % 2 -> treeFinder.addSame()
            current % 2 != next.size % 2 -> treeFinder.addDiff()
        }

        visited.add(current)
        for (n in next) {
            if (!visited.contains(n)) {
                findTrees(visited, treeFinder, n)
            }
        }
    }

    inner class TreeFinder {
        private var same = 0
        private var diff = 0

        fun getOddEvenTree(): Int =
            if (same == 1) 1
            else 0

        fun getReverseOddEvenTree(): Int =
            if (diff == 1) 1
            else 0

        fun addSame() { same++ }
        fun addDiff() { diff++ }
    }
}