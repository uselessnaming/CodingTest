package 프로그래머스

import kotlin.math.sqrt

class GetPrimeN {
    fun solution(n: Int, k: Int): Int{
        var answer = 0

        val s = n.toString(k).split('0')
        s.forEach{ newN ->
            if(newN != "" && isPrime(newN)) answer++
        }

        return answer
    }

    fun isPrime(n: String): Boolean{
        val convN = n.toLong()
        if (convN == 1L) return false
        if (convN == 2L) return true
        for(i in 2L .. sqrt(convN.toDouble()).toLong()){
            if(convN % i == 0L) return false
        }
        return true
    }
}