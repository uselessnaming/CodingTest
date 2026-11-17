package 프로그래머스

import java.util.LinkedList

class EqualizeTwoQueueSum {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        val max = queue1.size + queue2.size
        val q1 = LinkedList(queue1.toList())
        val q2 = LinkedList(queue2.toList())
        var sum1 = queue1.sumOf{it.toLong()}
        val sum2 = queue2.sumOf{it.toLong()}

        if ((sum1 + sum2) % 2 != 0L) return -1

        val target = (sum1 + sum2) / 2L
        var answer = 0

        while(sum1 != target) {
            if (q1.isEmpty() || q1.size == max) return -1

            if (answer >= 3 * max) return -1

            if (sum1 > target) {
                val discard = q1.poll()
                sum1 -= discard
                q2.add(discard)
            } else {
                val discard = q2.poll()
                sum1 += discard
                q1.add(discard)
            }
            answer++
        }

        return answer
    }
}