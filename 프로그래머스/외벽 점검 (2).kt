package 프로그래머스

class CheckWallSecond {
    private var answer = Int.MAX_VALUE
    private var n = 0
    private lateinit var dist: IntArray
    private lateinit var weak: IntArray

    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        this.n = n
        this.weak = weak
        this.dist = dist.sortedArray()

        for(idx in weak.indices) {
            dfs(1, idx, 0)
        }

        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun dfs(depth: Int, pos: Int, visited: Int) {
        if (depth > dist.size) return
        if (answer <= depth) return

        var visited = visited

        for (idx in weak.indices) {
            val nextPos = (pos + idx) % weak.size
            var diff = weak[nextPos] - weak[pos]

            if (diff < 0) diff += n
            if (diff > dist[dist.size - depth]) break

            visited = visited or (1 shl nextPos)
        }

        if (visited == (1 shl weak.size) - 1) {
            answer = minOf(answer, depth)
            return
        }

        for (idx in weak.indices) {
            if (visited and (1 shl idx) != 0) continue

            dfs(depth + 1, idx, visited)
        }
    }
}