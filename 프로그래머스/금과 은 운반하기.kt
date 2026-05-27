package 프로그래머스

import kotlin.math.min

class CarryGoldAndSilver {
    fun solution(a: Int, b: Int, g: IntArray, s: IntArray, w: IntArray, t: IntArray): Long {
        var start = 1L
        var end = 400000000000000L
        while (start < end) {
            val mid = (start + end) / 2
            if (check(mid, a, b, g, s, w, t)) {
                end = mid
            } else {
                start = mid + 1
            }
        }

        return end
    }

    private fun check(mid: Long, a: Int, b: Int, g: IntArray, s: IntArray, w: IntArray, t: IntArray): Boolean {
        var tot = 0L
        var gCarry = 0L
        var sCarry = 0L

        for(i in w.indices) {
            var cnt = mid / (t[i] * 2)
            if (mid % (t[i] * 2) >= t[i]) cnt++

            val maxCarry = min(cnt * w[i], (g[i] + s[i]).toLong())
            gCarry += min(g[i].toLong(), maxCarry)
            sCarry += min(s[i].toLong(), maxCarry)
            tot += maxCarry
        }

        return tot >= (a+b).toLong() && gCarry >= a && sCarry >= b
    }
}