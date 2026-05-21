package 프로그래머스

class MoveArea {
    private lateinit var visited: Array<BooleanArray>
    private var answer = 0
    private val dx = intArrayOf(0, 0, -1, 1)
    private val dy = intArrayOf(-1, 1, 0, 0)

    fun solution(land: Array<IntArray>, height: Int): Int {
        visited = Array(land.size) { BooleanArray(land[0].size) { false } }

        // 가장 최소 값인 곳에서 출발
        val (startX, startY) = getStartIdx(land)
        bfs(land, startX, startY, height)

        return answer
    }

    private fun getStartIdx(land: Array<IntArray>): Pair<Int, Int> {
        // for문으로 전탐색 후 가장 작은 값을 start 값으로 지정하고 return
        return 0 to 0
    }

    private fun bfs(land: Array<IntArray>, startX: Int, startY: Int, height: Int) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        visited[startX][startY] = true
        queue.add(startX to startY)

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            // height가 넘지 않는 구역 탐색
            visitNearArea(land, x, y, height)
            // height가 넘는 구역 과 넘지 않는 구역 사이 가중치가 가장 적은 곳 찾기
            val (nextX, nextY) = findBiggestWeight()
            // 가장 적은 곳과 혁제 위치의 값 차이를 answer에 추가
            answer += (land[nextX][nextY] - land[x][y])

            // 해당 구역을 start로 해서 재 탐색
            queue.add(nextX to nextY)
        }
    }

    private fun visitNearArea(land: Array<IntArray>, x: Int, y: Int, limit: Int) {
        val std = land[x][y]
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(x to y)

        while (queue.isNotEmpty()) {
            val (curX, curY) = queue.removeFirst()

            for (idx in 0 until 4) {
                val nx = curX + dx[idx]
                val ny = curY + dy[idx]

                if (nx in land.indices && ny in land[0].indices) {
                    if (!visited[nx][ny] && land[nx][ny] - std <= limit) {
                        visited[nx][ny] = true
                        queue.add(nx to ny)
                    }
                }
            }
        }
    }

    private fun findBiggestWeight(): Pair<Int, Int> {
        // visited 에서 true인 곳 중 false와 맞닿아 있는 곳을 확인하고
        // 이 경우 land에서 값을 비교하여 만약 min 값보다 작으면 위치를 기억
        // 가장 작은 위치의 Pair를 그대로 return
        return 0 to 0
    }
}