package 프로그래머스

import kotlin.math.abs

class EscapeMazeCommandSecond {
    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        val dist0 = abs(x - r) + abs(y - c)
        if (dist0 > k || (k - dist0) % 2 != 0) return IMPOSSIBLE

        val dx = intArrayOf(1, 0, 0, -1)
        val dy = intArrayOf(0, -1, 1, 0)
        val cmd = charArrayOf('d', 'l', 'r', 'u')

        val sb = StringBuilder()
        var cx = x
        var cy = y

        for (step in 1..k) {
            for (i in 0 until 4) {
                val nx = cx + dx[i]
                val ny = cy + dy[i]
                if (nx !in 1..n || ny !in 1..m) continue

                val remain = k - step
                val dist = abs(nx - r) + abs(ny - c)
                if (dist > remain || (remain - dist) % 2 != 0) continue

                sb.append(cmd[i])
                cx = nx
                cy = ny
                break
            }
        }

        return sb.toString()
    }

    companion object {
        const val IMPOSSIBLE = "impossible"
    }
}