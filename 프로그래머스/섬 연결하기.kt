package 프로그래머스

class ConnectIsland {
    private fun getParent(parent: IntArray, x: Int): Int {
        return if (parent[x] == x) x else getParent(parent, parent[x]).also { parent[x] = it }
    }

    private fun unionParent(parent: IntArray, x: Int, y: Int) {
        val x = getParent(parent, x)
        val y = getParent(parent, y)
        if (x < y) {
            parent[y] = x
        } else {
            parent[x] = y
        }
    }

    private fun findParent(parent: IntArray, x: Int, y: Int): Boolean {
        return getParent(parent, x) == getParent(parent, y)
    }

    fun solution(n: Int, costs: Array<IntArray>): Int {
        var answer = 0
        val parents = IntArray(n) { it }
        costs.sortWith(compareBy { it[2] })

        for (i in costs.indices) {
            if (!findParent(parents, costs[i][0], costs[i][1])) {
                unionParent(parents, costs[i][0], costs[i][1])
                answer += costs[i][2]
            }
        }

        return answer
    }
}