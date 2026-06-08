package 프로그래머스

import kotlin.math.abs

class PowerPlantRecovery {
    private val INF = Long.MAX_VALUE / 4

    fun solution(h: Int, grid: Array<String>, panels: Array<IntArray>, seqs: Array<IntArray>): Int {
        val k = panels.size
        val prereq = IntArray(k)

        for (seq in seqs) {
            val a = seq[0] - 1
            val b = seq[1] - 1

            prereq[b] = prereq[b] or (1 shl a)
        }

        val cost = buildCostTable(grid, panels)
        val start = k
        val dp = Array(1 shl k) { LongArray(k + 1) { INF } }

        dp[0][start] = 0

        for (mask in 0 until (1 shl k)) {
            for (last in 0..k) {
                val cur = dp[mask][last]

                if (cur == INF) continue

                for (next in 0 until k) {
                    if ((mask and (1 shl next)) != 0) continue
                    if ((mask and prereq[next]) != prereq[next]) continue

                    val nextMask = mask or (1 shl next)
                    val move = if (last == start) cost[0][next] else cost[last][next]

                    dp[nextMask][next] = minOf(dp[nextMask][next], cur + move)
                }
            }
        }

        val full = (1 shl k) - 1
        var answer = INF

        for (i in 0 until k) {
            answer = minOf(answer, dp[full][i])
        }

        return answer.toInt()
    }

    private fun buildCostTable(grid: Array<String>, panels: Array<IntArray>): Array<IntArray> {
        val n = grid.size
        val m = grid[0].length
        val k = panels.size

        var er = -1
        var ec = -1

        for (r in 0 until n) {
            for (c in 0 until m) {
                if (grid[r][c] == '@') {
                    er = r
                    ec = c
                }
            }
        }

        val points = ArrayList<Pos>()

        for (panel in panels) {
            points.add(Pos(panel[1] - 1, panel[2] - 1))
        }
        points.add(Pos(er, ec))

        val elevatorIdx = k
        val sameFloorDist = Array(k + 1) { IntArray(k + 1) }

        for (i in points.indices) {
            val dist = bfs(points[i].r, points[i].c, grid)

            for (j in points.indices) {
                sameFloorDist[i][j] = dist[points[j].r][points[j].c]
            }
        }

        val cost = Array(k) { IntArray(k) }

        for (i in 0 until k) {
            for (j in 0 until k) {
                if (i == j) continue

                val floorI = panels[i][0]
                val floorJ = panels[j][0]

                cost[i][j] = if (floorI == floorJ) {
                    sameFloorDist[i][j]
                } else {
                    sameFloorDist[i][elevatorIdx] + abs(floorI - floorJ) + sameFloorDist[elevatorIdx][j]
                }
            }
        }

        return cost
    }

    private fun bfs(sr: Int, sc: Int, grid: Array<String>): Array<IntArray> {
        val n = grid.size
        val m = grid[0].length
        val dist = Array(n) { IntArray(m) { -1 } }
        val q = ArrayDeque<Pair<Int, Int>>()

        dist[sr][sc] = 0
        q.add(sr to sc)

        val dr = intArrayOf(-1, 1, 0, 0)
        val dc = intArrayOf(0, 0, -1, 1)

        while (q.isNotEmpty()) {
            val (r, c) = q.removeFirst()

            for (idx in 0 until 4) {
                val nr = r + dr[idx]
                val nc = c + dc[idx]

                if (nr !in 0 until n || nc !in 0 until m) continue
                if (grid[nr][nc] == '#') continue
                if (dist[nr][nc] != -1) continue

                dist[nr][nc] = dist[r][c] + 1
                q.add(nr to nc)
            }
        }

        return dist
    }

    data class Panel(
        val floor: Int,
        val r: Int,
        val c: Int
    )

    data class Pos(
        val r: Int,
        val c: Int
    )
}