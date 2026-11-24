package 프로그래머스

import kotlin.math.*

class GetPrimeInK {
    fun solution(n: Int, k: Int): Int{
        val split = n.toString(k).split('0')
        var cnt = 0

        for(s in split) {
            if (s.isNotEmpty() && isPrime(s.toLong())) cnt++
        }

        return cnt
    }

    private fun isPrime(n: Long): Boolean {
        if (n == 1L) return false
        if (n == 2L) return true
        if (n % 2 == 0L) return false

        var i = 3L
        while(i * i <= n) {
            if (n % i == 0L) return false
            i += 2
        }
        return true
    }
}