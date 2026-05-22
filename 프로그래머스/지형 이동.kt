package 프로그래머스

import java.util.PriorityQueue
import kotlin.math.abs

class MoveArea {
    private val dx = intArrayOf(0, 0, -1, 1)
    private val dy = intArrayOf(-1, 1, 0, 0)

    data class Node(
        val x: Int,
        val y: Int,
        val cost: Int
    )

    fun solution(land: Array<IntArray>, height: Int): Int {
        val n = land.size
        val visited = Array(n) { BooleanArray(n) { false } }

        val pq = PriorityQueue<Node> { a, b ->
            a.cost - b.cost
        }
        pq.add(Node(0, 0, 0))

        var answer = 0

        while(pq.isNotEmpty()) {
            val current = pq.poll()

            val x = current.x
            val y = current.y
            val cost = current.cost

            if (visited[x][y]) continue
            visited[x][y] = true

            answer += cost

            for (dir in 0 until 4) {
                val nx = x + dx[dir]
                val ny = y + dy[dir]

                if (nx !in 0 until n || ny !in 0 until n) continue
                if (visited[nx][ny]) continue

                val diff = abs(land[x][y] - land[nx][ny])

                val nextCost = if (diff <= height) 0 else diff

                pq.add(Node(nx, ny, nextCost))
            }
        }

        return answer
    }
}