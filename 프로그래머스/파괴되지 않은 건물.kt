package 프로그래머스

class NotDemolishedBuilding {
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        val r = board.size
        val c = board[0].size
        val diff = Array(r + 1) { IntArray(c + 1) }

        for (s in skill) {
            val amount = if (s[0] == ATK) -s[5] else s[5]
            val r1 = s[1]
            val r2 = s[3]
            val c1 = s[2]
            val c2 = s[4]

            diff[r1][c1] += amount
            diff[r1][c2 + 1] -= amount
            diff[r2 + 1][c1] -= amount
            diff[r2 + 1][c2 + 1] += amount
        }

        for (i in 0 until r) {
            for (j in 1 until c) {
                diff[i][j] += diff[i][j - 1]
            }
        }

        for (j in 0 until c) {
            for (i in 1 until r) {
                diff[i][j] += diff[i - 1][j]
            }
        }

        var cnt = 0
        for (i in 0 until r) {
            for (j in 0 until c) {
                if (board[i][j] + diff[i][j] > 0) cnt++
            }
        }
        return cnt
    }

    companion object {
        const val ATK = 1
        const val HEAL = 2
    }
}