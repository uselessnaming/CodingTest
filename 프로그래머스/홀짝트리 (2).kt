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
            next.size % 2 == 0 && current % 2 == 0 -> treeFinder.addEven()
            next.size % 2 == 1 && current % 2 == 0 -> treeFinder.addReverseEven()
            next.size % 2 == 0 && current % 2 == 1 -> treeFinder.addReverseOdd()
            next.size % 2 == 1 && current % 2 == 1 -> treeFinder.addOdd()
        }

        visited.add(current)
        for (n in next) {
            if (!visited.contains(n)) {
                findTrees(visited, treeFinder, n)
            }
        }
    }

    inner class TreeFinder {
        private var odd = 0
        private var even = 0
        private var reverseOdd = 0
        private var reverseEven = 0

        fun getOddEvenTree(): Int =
            if ((odd == 1 && even == 0) || (odd == 0 && even == 1)) 1
            else 0

        fun getReverseOddEvenTree(): Int = if (reverseOdd == 1 && reverseEven == 0) 1
        else if (reverseOdd == 0 && reverseEven == 1) 1
        else 0

        fun addOdd() = odd++
        fun addEven() = even++
        fun addReverseOdd() = reverseOdd++
        fun addReverseEven() = reverseEven++
    }
}