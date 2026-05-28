package 프로그래머스

class FillPuzzle {
    private val boards = (1..6).toList()
        .associateWith { mutableListOf<List<Pair<Int, Int>>>() }
        .toMutableMap()
    private val blocks = (1..6).toList()
        .associateWith { mutableListOf<List<Pair<Int, Int>>>() }
        .toMutableMap()
    private val dx = listOf(-1, 1, 0, 0)
    private val dy = listOf(0, 0, -1, 1)
    private var answer = Array(7) { 0 }

    // 문제 해결 흐름 (블록 셋 마련 -> 구멍 셋 마련 -> 백트래킹 조회 (rotate 활용))

    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        // 블록이 존재하는 위치를 블록 개수로 나눠서 정리
        getBoard(game_board)
        getBlock(table)
        println("table : $blocks")
        println("board: $boards")

        // blocks를 돌려가면서 백트래킹으로 전 탐색
        for (num in boards.keys) {
            println("num : $num")
            if (boards[num].isNullOrEmpty() || blocks[num].isNullOrEmpty()) continue

            fillBlocks(num)
        }
        // 블록마다 넣을 수 있는 곳에 다 넣어보면서 점수를 체크하고 최대 값 return

        return answer.sum()
    }

    private fun getBlock(table: Array<IntArray>) {
        val visited = Array(table.size) { BooleanArray(table[0].size) { false } }

        for (i in table.indices) {
            for (j in table[0].indices) {
                if (!visited[i][j] && table[i][j] == 1) {
                    val nodes = bfs(i, j, 1, table, visited)
                    blocks[nodes.size]!!.add(nodes)
                }
            }
        }
    }

    private fun getBoard(table: Array<IntArray>) {
        val visited = Array(table.size) { BooleanArray(table[0].size) { false } }

        for (i in table.indices) {
            for (j in table[0].indices) {
                if (!visited[i][j] && table[i][j] == 0) {
                    val nodes = bfs(i, j, 0, table, visited).normalize()
                    boards[nodes.size]!!.add(nodes)
                }
            }
        }
    }

    private fun bfs(
        x: Int,
        y: Int,
        target: Int,
        table: Array<IntArray>,
        visited: Array<BooleanArray>
    ): List<Pair<Int, Int>> {
        val queue = ArrayDeque<List<Int>>()
        val result = mutableListOf<Pair<Int, Int>>()
        queue.add(listOf(x, y))
        result.add(x to y)
        visited[x][y] = true

        while (queue.isNotEmpty()) {
            val (nextX, nextY) = queue.removeFirst()

            for (idx in 0 until 4) {
                val nx = nextX + dx[idx]
                val ny = nextY + dy[idx]

                if (nx in 0 until table.size && ny in 0 until table[0].size) {
                    if (visited[nx][ny]) continue

                    if (table[nx][ny] == target) {
                        visited[nx][ny] = true
                        queue.add(listOf(nx, ny))
                        result.add(nx to ny)
                    }
                }
            }
        }

        return result
    }

    private fun fillBlocks(n: Int) {
        println("fillBlock : $n")
        val used = BooleanArray(boards[n]!!.size) { false }

        outer@for (idx in blocks[n]!!.indices) {
            var curBlock = blocks[n]!![idx]

            for(idx in 0 until 4) {
                curBlock = rotate(curBlock).normalize()
                println("rotated block : $curBlock")
                if (isPossible(n, curBlock, used)) {
                    answer[n] += n
                    continue@outer
                }
            }
        }
        println("answer : ${answer.toList()}")
    }

    private fun rotate(dots: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        // 90도 우회전하고자 할 때 어떻게 배열을 변경하면 될지 고민해보기
        return dots.map { (x, y) -> y to -x }
    }

    private fun isPossible(n: Int, block: List<Pair<Int, Int>>, used: BooleanArray): Boolean {
        // block이 visited되지 않은 blocks[n] 중에서 들어갈 수 있는 곳이 있다면 true return
        //  true라면 return 전에 visited 처리
        // 기본 값은 false로 유지
        for (idx in boards[n]!!.indices) {
            if (used[idx]) continue

            if (block.toSet() == boards[n]!![idx].toSet()) {
                used[idx] = true
                return true
            }
        }

        return false
    }

    private fun List<Pair<Int, Int>>.normalize(): List<Pair<Int, Int>> {
        val minX = this.minOf { it.first }
        val minY = this.minOf { it.second }

        return this.map { (x, y) -> (x - minX) to (y - minY) }
            .sortedWith(compareBy<Pair<Int, Int>> { it.first }
                .thenBy { it.second })
    }
}