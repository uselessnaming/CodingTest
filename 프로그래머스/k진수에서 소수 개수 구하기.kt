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
        if (convN == 2L) return true
        if (convN % 2 == 0L) return false

        var i = 3L
        while(i * i <= convN) {
            if (convN % i == 0L) return false
            i += 2
        }
        return true
    }
}