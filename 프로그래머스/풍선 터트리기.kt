package 프로그래머스

class PoppingBalloon {
    fun solution(a: IntArray): Int {
        var answer = 0
        val leftMost = IntArray(a.size) { 0 }.apply { this[0] = a[0] }
        val rightMost = IntArray(a.size) { 0 }.apply { this[this.lastIndex] = a.last() }

        for (idx in 1 until a.size) {
            leftMost[idx] = a[idx].coerceAtMost(leftMost[idx - 1])
        }

        for (idx in a.size - 2 downTo 1) {
            rightMost[idx] = a[idx].coerceAtMost(rightMost[idx + 1])
        }

        for (idx in a.indices) {
            if (idx == 0 || idx == a.lastIndex) {
                answer++
                continue
            }

            if (a[idx] < leftMost[idx - 1] || a [idx] < rightMost[idx + 1]) {
                answer++
            }
        }

        return answer
    }
}