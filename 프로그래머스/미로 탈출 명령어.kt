package 프로그래머스

import kotlin.math.abs

class EscapeMazeCommand {
    private val d = arrayOf(
        1 to 0,
        0 to -1,
        0 to 1,
        -1 to 0
    )
    private val cmdArray = arrayOf("d", "l", "r", "u")
    private var n = 0
    private var m = 0
    private var k = 0
    private var answer = IMPOSSIBLE

    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        this.n = n
        this.m = m
        this.k = k

        dfs(r, c, Node(x, y, 0, ""))

        return answer
    }

    fun dfs(targetX: Int, targetY: Int, currentNode: Node) {
        if (answer != IMPOSSIBLE) return

        if (currentNode.x == targetX && currentNode.y == targetY && currentNode.cnt == k) {
            answer = currentNode.cmd
            return
        }

        for (i in d.indices) {
            val nx = currentNode.x + d[i].first
            val ny = currentNode.y + d[i].second

            if (nx !in 1..n || ny !in 1..m) continue

            val remain = k - (currentNode.cnt + 1)
            val dist = abs(targetX - nx) + abs(targetY - ny)
            if (dist > remain || (remain - dist) % 2 != 0) continue

            dfs(targetX, targetY, Node(nx, ny, currentNode.cnt + 1, currentNode.cmd + cmdArray[i]))
        }
    }

    data class Node(
        val x: Int,
        val y: Int,
        val cnt: Int,
        val cmd: String
    )

    companion object {
        const val IMPOSSIBLE = "impossible"
    }
}