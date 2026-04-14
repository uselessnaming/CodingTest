package 프로그래머스

import kotlin.math.min

// 비트 마스킹 관련 문제를 풀어보자

class CheckWall {
    private var answer = Int.MAX_VALUE
    private var n = 0
    private lateinit var dst: IntArray
    private lateinit var weak: IntArray

    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        this.n = n
        this.dst = dist
        this.weak = weak
        dst.sort()

        for (i in weak.indices) {
            dfs(1, i, 0)
        }

        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun dfs(depth: Int, pos: Int, isVisited: Int) {
        if (depth > dst.size) return
        if (answer <= depth) return

        var visited = isVisited

        for (i in weak.indices) {
            val nextPos = (pos + i) % weak.size

            var diff = weak[nextPos] - weak[pos]

            if (diff < 0) {
                diff += n
            }

            if (diff > dst[dst.size - depth]) break

            visited = visited or (1 shl nextPos)
        }

        if (visited == (1 shl weak.size) - 1) {
            answer = min(answer, depth)
            return
        }

        for (i in weak.indices) {
            if (visited and (1 shl i) != 0) continue
            dfs(depth + 1, i, visited)
        }
    }
}