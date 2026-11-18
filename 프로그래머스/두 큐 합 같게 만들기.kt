package 프로그래머스

class EqualizeTwoQueueSum {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        val max = queue1.size + queue2.size

        val q = queue1 + queue2
        var start = 0
        var end = queue1.size - 1

        var sum1 = queue1.sumOf { it.toLong() }
        val total = sum1 + queue2.sumOf { it.toLong() }

        if (total % 2 != 0L) return -1

        val target = total / 2L
        var answer = 0

        while (sum1 != target) {
            if (answer >= max * 2) return -1

            if (sum1 > target) {
                sum1 -= q[start % max]
                start++
            } else {
                end++
                sum1 += q[end % max]
            }
            answer++
        }

        return answer
    }
}