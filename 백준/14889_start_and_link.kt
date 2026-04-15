package 백준

import kotlin.math.abs

class StartAndLink {
    private var n = 0
    private val values: MutableList<List<Int>> = mutableListOf()
    private var answer = Int.MAX_VALUE

    fun run() {
        n = readln().toInt()

        repeat(n) {
            val line = readln().split(" ").map { it.toInt() }
            values.add(line)
        }

        for (i in 0 until n) {
            val visited = MutableList(n) { false }
            visited[i] = true
            dfs(1, i, visited)
        }

        println(answer)
    }

    private fun dfs(depth: Int, start: Int, visited: MutableList<Boolean>) {
        if (depth == n / 2) {
            val score = calculateScore(visited)
            if (score < answer) answer = score
            return
        }

        for (i in start + 1 until n) {
            visited[i] = true
            dfs(depth + 1, i, visited)
            visited[i] = false
        }
    }

    private fun calculateScore(visited: MutableList<Boolean>): Int {
        val startScoreIndices = visited.mapIndexed{ idx, value -> idx to value }
            .filter{ it.second }
        val linkScoreIndices = visited.mapIndexed{ idx, value -> idx to value }
            .filter{ !it.second }

        var startScore = 0
        var linkScore = 0

        for((i, element) in startScoreIndices.withIndex()) {
            for (j in i until startScoreIndices.size) {
                val x = element.first
                val y = startScoreIndices[j].first
                startScore += values[x][y] + values[y][x]
            }
        }

        for((i, element) in linkScoreIndices.withIndex()) {
            for (j in i until linkScoreIndices.size) {
                val x = element.first
                val y = linkScoreIndices[j].first
                linkScore += values[x][y] + values[y][x]
            }
        }

        return abs(startScore - linkScore)
    }
}