package 프로그래머스

class SelectCourseUnionFind {
    private lateinit var parents: IntArray

    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val type = IntArray(n + 1)
        for (g in gates) type[g] = GATE
        for (s in summits) type[s] = SUMMIT

        parents = IntArray(n + 1) { it }
        val hasGate = BooleanArray(n + 1) { type[it] == GATE }
        val best = IntArray(n + 1) { INF }

        val pendHead = IntArray(n + 1) { -1 }
        val pendTail = IntArray(n + 1) { -1 }
        val pendSummit = IntArray(paths.size)
        val pendNext = IntArray(paths.size)
        var pendCnt = 0
        val sorted = paths.sortedBy { it[2] }

        for ((a, b, w) in sorted) {
            if (type[a] == SUMMIT && type[b] == SUMMIT) continue
            if (type[a] == SUMMIT || type[b] == SUMMIT) {
                val s = if (type[a] == SUMMIT) a else b
                val x = if (type[a] == SUMMIT) b else a

                if (type[x] == GATE) {
                    if (best[s] == INF) best[s] = w
                } else {
                    val r = find(x)
                    if (hasGate[r]) {
                        if (best[s] == INF) best[s] = w
                    } else {
                        pendSummit[pendCnt] = s
                        pendNext[pendCnt] = -1

                        if (pendHead[r] == -1) {
                            pendHead[r] = pendCnt
                        } else {
                            pendNext[pendTail[r]] = pendCnt
                        }

                        pendTail[r] = pendCnt
                        pendCnt++
                    }
                }
                continue
            }
            if (type[a] == GATE && type[b] == GATE) continue

            val ra = find(a)
            val rb = find(b)
            if (ra == rb) continue

            parents[rb] = ra

            if (pendHead[rb] != -1) {
                if (pendHead[ra] == -1) {
                    pendHead[ra] = pendHead[rb]
                    pendTail[ra] = pendTail[rb]
                } else {
                    pendNext[pendTail[ra]] = pendHead[rb]
                    pendTail[ra] = pendTail[rb]
                }
            }
            hasGate[ra] = hasGate[ra] || hasGate[rb]

            if (hasGate[ra] && pendHead[ra] != -1) {
                var e = pendHead[ra]
                while (e != -1) {
                    val s = pendSummit[e]
                    if (best[s] == INF) {
                        best[s] = w
                    }
                    e = pendNext[e]
                }

                pendHead[ra] = -1
                pendTail[ra] = -1
            }
        }

        val answer = intArrayOf(INF, INF)
        for (s in summits.sorted()) {
            if (best[s] < answer[1]) {
                answer[0] = s
                answer[1] = best[s]
            }
        }

        return answer
    }

    private fun find(x: Int): Int {
        var r = x
        while (parents[r] != r) r = parents[r]

        var c = x
        while (parents[c] != c) {
            val nx = parents[c]
            parents[c] = r
            c = nx
        }

        return r
    }

    companion object {
        const val GATE = 1
        const val SUMMIT = 2
        const val INF = Int.MAX_VALUE
    }
}