package 프로그래머스

class DisappearingScaffold {
    private val dx = listOf(-1, 1, 0, 0)
    private val dy = listOf(0, 0, -1, 1)

    private fun moveA(board: Array<IntArray>, aloc: IntArray, bloc: IntArray): Pair<String, Int> {
        var isFinished = true
        for (idx in dx.indices) {
            val nx = aloc[0] + dx[idx]
            val ny = aloc[1] + dy[idx]

            if (nx !in board.indices || ny !in board[0].indices) continue

            if (board[nx][ny] == 0) continue

            isFinished = false
        }

        if (isFinished) return "B" to 0
        if (board[aloc[0]][aloc[1]] == 0) return "B" to 0

        board[aloc[0]][aloc[1]] = 0

        var canWin = false
        var minTurn = Int.MAX_VALUE
        var maxTurn = 0

        for (idx in dx.indices) {
            val nx = aloc[0] + dx[idx]
            val ny = aloc[1] + dy[idx]

            if (nx !in board.indices || ny !in board[0].indices) continue
            if (board[nx][ny] == 0) continue

            val result = moveB(board, intArrayOf(nx, ny), bloc)
            if (result.first == "A") {
                canWin = true
                minTurn = minTurn.coerceAtMost(result.second)
            } else if (!canWin) {
                maxTurn = maxTurn.coerceAtLeast(result.second)
            }
        }

        board[aloc[0]][aloc[1]] = 1
        return if (canWin) "A" to minTurn + 1 else "B" to maxTurn + 1
    }

    private fun moveB(board: Array<IntArray>, aloc: IntArray, bloc: IntArray): Pair<String, Int> {
        var isFinished = true
        for (idx in dx.indices) {
            val nx = bloc[0] + dx[idx]
            val ny = bloc[1] + dy[idx]

            if (nx !in board.indices || ny !in board[0].indices) continue

            if (board[nx][ny] == 0) continue

            isFinished = false
        }

        if (isFinished) return "A" to 0
        if (board[bloc[0]][bloc[1]] == 0) return "A" to 0

        board[bloc[0]][bloc[1]] = 0

        var canWin = false
        var minTurn = Int.MAX_VALUE
        var maxTurn = 0

        for(idx in dx.indices) {
            val nx = bloc[0] + dx[idx]
            val ny = bloc[1] + dy[idx]

            if (nx !in board.indices || ny !in board[0].indices) continue
            if (board[nx][ny] == 0) continue

            val result = moveA(board, aloc, intArrayOf(nx, ny))
            if (result.first == "B") {
                canWin = true
                minTurn = minTurn.coerceAtMost(result.second)
            } else if (!canWin) {
                maxTurn = maxTurn.coerceAtLeast(result.second)
            }
        }

        board[bloc[0]][bloc[1]] = 1
        return if (canWin) "B" to minTurn + 1 else "A" to maxTurn + 1
    }

    fun solution(board: Array<IntArray>, aloc: IntArray, bloc: IntArray): Int {
        return moveA(board, aloc, bloc).second
    }
}