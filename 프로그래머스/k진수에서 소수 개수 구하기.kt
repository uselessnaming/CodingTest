package 프로그래머스

import kotlin.math.*

class GetPrimeInK {

    fun solution(n: Int, k: Int): Int{
        val split = n.toString(k).split('0')
        var cnt = 0

        for(s in split) {
            if (s.isNotEmpty() && isPrime(s)) cnt++
        }

        return cnt
    }

    private fun isPrime(n: String): Boolean {
        val convN = n.toLong()

        if (convN == 1L) return false

        for (i in 2..sqrt(convN.toDouble()).toLong()) {
            if (convN % i == 0L) return false
        }

        return true
    }
}