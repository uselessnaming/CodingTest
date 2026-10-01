package 프로그래머스

class NQueen {
    fun solution(n: Int): Int {
        var cnt = 0
        val full = (1 shl n) - 1

        fun dfs(col: Int, diag1: Int, diag2: Int) {
            if (col == full) {
                cnt++
                return
            }

            var available = full and (col or diag1 or diag2).inv()

            while(available != 0) {
                val pos = available.takeLowestOneBit()
                available -= pos

                dfs(col or pos, (diag1 or pos) shl 1, (diag2 or pos) shr 1)
            }
        }

        dfs(0, 0, 0)

        return cnt
    }
}