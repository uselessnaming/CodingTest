package 프로그래머스

class MatrixAndArray {
    private val left = ArrayDeque<Int>()
    private val middle = ArrayDeque<ArrayDeque<Int>>()
    private val right = ArrayDeque<Int>()

    fun solution(rc: Array<IntArray>, operations: Array<String>): Array<IntArray> {
        setArray(rc)
        println("left ; $left")
        println("middle ; $middle")
        println("right ; $right")

        for (op in operations) {
            when (op) {
                "Rotate" -> rotate(rc.size, rc[0].size)
                "ShiftRow" -> shift()
            }
        }

        return getAnswer()
    }

    private fun rotate(r: Int, c: Int) {
        middle[0].addFirst(left.removeFirst())
        right.addFirst(middle[0].removeLast())
        middle[r - 1].addLast(right.removeLast())
        left.addLast(middle[r - 1].removeFirst())
    }

    private fun shift() {
        left.addFirst(left.removeLast())
        middle.addFirst(middle.removeLast())
        right.addFirst(right.removeLast())
    }

    private fun setArray(rc: Array<IntArray>) {
        // left 추가
        for (idx in rc.indices) {
            left.add(rc[idx][0])
        }

        // right 추가
        for (idx in rc.indices) {
            right.add(rc[idx][rc[0].size - 1])
        }

        // middle 추가
        for (idx in rc.indices) {
            middle.add(ArrayDeque(rc[idx].slice(1 until rc[0].size - 1)))
        }
    }

    private fun getAnswer(): Array<IntArray> {
        val answer = mutableListOf<List<Int>>()
        for (idx in left.indices) {
            val temp = mutableListOf<Int>()
            temp.add(left[idx])
            temp.addAll(middle[idx])
            temp.add(right[idx])
            answer.add(temp)
        }

        return answer.map { it.toIntArray() }.toTypedArray()
    }
}