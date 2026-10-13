package 프로그래머스

class PerfectCrimeSecond {
    private val MAX = 120
    private val visited = Array(MAX + 3) { BooleanArray(MAX + 3) { false } }

    fun solution(info: Array<IntArray>, n: Int, m: Int): Int {
        val itemCnt = info.size
        visited[0][0] = true

        for (i in 0 until itemCnt) {
            val nextVisited = Array(MAX + 3) { BooleanArray(MAX + 3) { false } }
            val traceA = info[i][0]
            val traceB = info[i][1]

            for (a in 0 until n) {
                for (b in 0 until m) {
                    if (!visited[a][b]) continue

                    if (a + traceA < n) nextVisited[a + traceA][b] = true
                    if (b + traceB < m) nextVisited[a][b + traceB] = true
                }
            }

            for (a in 0 until n) {
                for (b in 0 until m) {
                    visited[a][b] = nextVisited[a][b]
                }
            }
        }

        for (a in 0 until n) {
            for (b in 0 until m) {
                if (visited[a][b]) return a
            }
        }

        return -1
    }
}