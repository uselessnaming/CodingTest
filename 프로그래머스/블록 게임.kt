package 프로그래머스

class BlockGame {
    private val dx = intArrayOf(-1, 0, 1, 0)
    private val dy = intArrayOf(0, 1, 0, -1)

    fun solution(board: Array<IntArray>): Int {
        val size = board.size

        var blocks = mutableListOf<List<Pair<Int, Int>>>()
        val disableColumn = IntArray(size) { size + 1 }
        var block = mutableListOf<Pair<Int, Int>>()

        for (i in 0 until size) {
            for (j in 0 until size) {
                if (board[i][j] != 0) {
                    block = mutableListOf()
                    getBlock(block, i, j, board, size)
                    blocks.add(block.toList())
                }
            }
        }

        var disableRemove = -1
        while (disableRemove != 0) {
            disableRemove = 0
            val temp = mutableListOf<List<Pair<Int, Int>>>()
            for (block in blocks) {
                val able = checkRemovable(block, disableColumn)
                if (able) {
                    temp.add(block)
                } else {
                    for (b in block) {
                        val (x, y) = b
                        if (disableColumn[y] > x) {
                            disableColumn[y] = x
                        }
                        disableRemove += 1
                    }
                }
            }
            blocks = temp
        }

        return blocks.size
    }

    private fun getBlock(result: MutableList<Pair<Int, Int>>, x: Int, y: Int, board: Array<IntArray>, size: Int) {
        val shape = board[x][y]

        board[x][y] = 0
        result.add(x to y)

        for (idx in 0 until 4) {
            val nx = x + dx[idx]
            val ny = y + dy[idx]

            if (nx in 0 until size && ny in 0 until size && board[nx][ny] == shape) {
                getBlock(result, nx, ny, board, size)
            }
        }
    }

    private fun checkRemovable(block: List<Pair<Int, Int>>, disableColumn: IntArray): Boolean {
        val (x, y) = block[0]
        val vector = mutableListOf<Pair<Int, Int>>()

        val availableBlocks = listOf(
            listOf(0 to 0, 1 to 0, 1 to 1, 1 to 2),
            listOf(0 to 0, 1 to 0, 2 to 0, 2 to -1),
            listOf(0 to 0, 1 to 0, 2 to 0, 2 to 1),
            listOf(0 to 0, 1 to 0, 1 to -1, 1 to -2),
            listOf(0 to 0, 1 to 0, 1 to 1, 1 to -1)
        )

        for (idx in 0 until 4) {
            vector.add(block[idx].first - x to block[idx].second - y)
        }

        return if (vector == availableBlocks[0]) {
            disableColumn[y + 1] > x && disableColumn[y + 2] > x
        } else if (vector == availableBlocks[1]) {
            disableColumn[y - 1] > x + 1
        } else if (vector == availableBlocks[2]) {
            disableColumn[y + 1] > x + 1
        } else if (vector == availableBlocks[3]) {
            disableColumn[y - 1] > x && disableColumn[y - 2] > x
        } else if (vector == availableBlocks[4]) {
            disableColumn[y - 1] > x && disableColumn[y + 1] > x
        } else {
            false
        }
    }
}