package 프로그래머스

class ArchaeologicalDiscovery {
    private val dir = listOf(
        0 to 0,
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )
    private lateinit var clockHands: Array<IntArray>
    private var n = 0
    private var answer = Int.MAX_VALUE

    fun solution(clockHands: Array<IntArray>): Int {
        this.clockHands = clockHands
        n = clockHands.size

        dfs(0, IntArray(n))

        return answer
    }

    private fun dfs(col: Int, firstRow: IntArray) {
        if (col == n) {
            simulate(firstRow)
            return
        }

        for (cnt in 0 until 4) {
            firstRow[col] = cnt
            dfs(col + 1, firstRow)
        }
    }

    private fun simulate(firstRow: IntArray) {
        val board = Array(n) { r -> clockHands[r].clone() }
        var cnt = 0

        for (c in 0 until n) {
            repeat(firstRow[c]) {
                rotate(board, 0, c)
            }
            cnt += firstRow[c]
        }

        for (r in 0 until n - 1) {
            for (c in 0 until n) {
                val need = (4 - board[r][c]) % 4

                if (need == 0) continue

                repeat(need) {
                    rotate(board, r + 1, c)
                }

                cnt += need
            }
        }

        for (c in 0 until n) {
            if (board[n - 1][c] != 0) {
                return
            }
        }

        answer = minOf(answer, cnt)
    }

    private fun rotate(board: Array<IntArray>, x: Int, y: Int) {
        for ((dx, dy) in dir) {
            val nx = x + dx
            val ny = y + dy

            if (nx !in 0 until n || ny !in 0 until n) continue

            board[nx][ny] = (board[nx][ny] + 1) % 4
        }
    }
}