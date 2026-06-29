package 프로그래머스

class BuildRoad {
    private lateinit var costs: Array<Array<IntArray>>
    private val dx = listOf(1, 0, -1, 0)
    private val dy = listOf(0, 1, 0, -1)

    fun solution(board: Array<IntArray>): Int {
        bfs(board)
        return costs.last().last().min()
    }

    private fun bfs(board: Array<IntArray>) {
        val queue = ArrayDeque<List<Int>>()
        costs = Array(board.size) { Array(board[0].size) { IntArray(4) { Int.MAX_VALUE } } }

        costs[0][0][0] = 0
        costs[0][0][1] = 0
        queue.add(listOf(0, 0, 0))
        queue.add(listOf(0, 0, 1))

        while (queue.isNotEmpty()) {
            val (curX, curY, befDir) = queue.removeFirst()

            for (idx in dx.indices) {
                val nx = curX + dx[idx]
                val ny = curY + dy[idx]

                if (nx !in board.indices) continue
                if (ny !in board.indices) continue
                if (board[nx][ny] == 1) continue

                val nextCost =
                    if (befDir == idx) costs[curX][curY][befDir] + 100 else costs[curX][curY][befDir] + 600

                if (nextCost >= costs[nx][ny][idx]) continue

                costs[nx][ny][idx] = minOf(costs[nx][ny][idx], nextCost)
                queue.add(listOf(nx, ny, idx))
            }
        }
    }
}