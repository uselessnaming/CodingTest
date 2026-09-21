package 프로그래머스

class HidingCactus {
    fun solution(m: Int, n: Int, h: Int, w: Int, drops: Array<IntArray>): IntArray {
        val totalIdx = m * n
        val time = IntArray(totalIdx) { drops.size + 1 }

        for (idx in drops.indices) {
            val (dropX, dropY) = drops[idx]
            time[dropX * n + dropY] = idx + 1
        }

        val outRow = m - h + 1
        val outCol = n - w + 1
        val rowMin = IntArray(outCol * m)
        val dq1 = IntArray(n)

        for (r in 0 until m) {
            var head = 0
            var tail = 0
            val base = r * n
            val outBase = r * outCol

            for (c in 0 until n) {
                val v = time[base + c]

                while (tail > head && time[base + dq1[tail - 1]] >= v) {
                    tail--
                }
                dq1[tail++] = c

                if (dq1[head] <= c - w) {
                    head++
                }

                if (c >= w - 1) {
                    rowMin[outBase + (c - w + 1)] = time[base + dq1[head]]
                }
            }
        }

        val colMin = IntArray(outRow * outCol)
        val dq2 = IntArray(m)

        for (j in 0 until outCol) {
            var head = 0
            var tail = 0

            for (r in 0 until m) {
                val v = rowMin[r * outCol + j]

                while(tail > head && rowMin[dq2[tail - 1] * outCol + j] >= v) {
                    tail--
                }
                dq2[tail++] = r

                if (dq2[head] <= r - h) head++

                if (r >= h - 1) colMin[(r - h + 1) * outCol + j] = rowMin[dq2[head] * outCol + j]
            }
        }

        var bestVal = -1
        var bestR = 0
        var bestC = 0

        for (i in 0 until outRow) {
            for (j in 0 until outCol) {
                val v = colMin[i * outCol + j]
                if (v > bestVal) {
                    bestVal = v
                    bestR = i
                    bestC = j
                }
            }
        }

        return intArrayOf(bestR, bestC)
    }
}