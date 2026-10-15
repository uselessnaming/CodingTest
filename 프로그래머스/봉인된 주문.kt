package 프로그래머스

import kotlin.comparisons.compareBy
import kotlin.math.pow

class ForbiddenSpell {
    private val MAX = 26
    fun solution(n: Long, bans: Array<String>): String {
        var n = n

        bans.sortedWith(compareBy({ it.length }, { it })).forEach { ban ->
            if (n >= ban.toOrder()) n++
        }

        return n.toSpell()
    }

    private fun String.toOrder(): Long {
        var res = 0L
        this.reversed().forEachIndexed { idx, ch ->
            val alpha = ch - 'a' + 1
            res += (MAX.toDouble().pow(idx).toLong() * alpha)
        }

        return res
    }

    private fun Long.toSpell(): String {
        val sb = StringBuilder()
        var n = this

        while (n > 0) {
            n--
            sb.append('a' + (n % MAX).toInt())
            n /= MAX
        }

        return sb.reverse().toString()
    }
}