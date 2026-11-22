package 프로그래머스

class MatrixAndArraySecond {
    private val left = ArrayDeque<Int>()
    private val right = ArrayDeque<Int>()
    private val middle = ArrayDeque<ArrayDeque<Int>>()

    fun solution(rc: Array<IntArray>, operations: Array<String>): Array<IntArray> {
        val r = rc.size
        val c = rc[0].size

        split(rc, r, c)

        for (op in operations) {
            when (op) {
                ROTATE -> rotate()
                SHIFT -> shiftRow()
            }
        }

        return getResult(r, c)
    }

    private fun split(rc: Array<IntArray>, r: Int, c: Int) {
        for (i in rc.indices) {
            val m = ArrayList<Int>()
            for (j in rc[i].indices) {
                if (j == 0) {
                    left.add(rc[i][j])
                } else if (j == c - 1) {
                    right.add(rc[i][j])
                } else {
                    m.add(rc[i][j])
                }
            }
            middle.add(ArrayDeque(m))
        }
    }

    private fun rotate() {
        if (middle.first().isEmpty()) {
            right.addFirst(left.removeFirst())
            left.add(right.removeLast())
            return
        }
        right.addFirst(middle.first().removeLast())
        middle.first().addFirst(left.removeFirst())
        middle.last().add(right.removeLast())
        left.add(middle.last().removeFirst())
    }

    private fun shiftRow() {
        left.addFirst(left.removeLast())
        right.addFirst(right.removeLast())
        middle.addFirst(middle.removeLast())
    }

    private fun getResult(r: Int, c: Int): Array<IntArray> {
        val result = Array(r) { IntArray(c) { 0 } }

        for (i in 0 until r) {
            val m = middle.removeFirst()
            for (j in 0 until c) {
                if (j == 0) result[i][j] = left.removeFirst()
                else if (j == c - 1) result[i][j] = right.removeFirst()
                else result[i][j] = m.removeFirst()
            }
        }

        return result
    }

    companion object {
        const val ROTATE = "Rotate"
        const val SHIFT = "ShiftRow"
    }
}