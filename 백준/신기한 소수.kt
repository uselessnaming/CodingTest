package 백준

import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.time.measureTimedValue

// 백준 2023번 신기한 소수
// 완성된 숫자를 찾지 말고
// 조건에 만족하는 수를 만들어서 출력
// >> 이렇게 하니까 3.7초까지 줄어들었네 (기존 8초때)

class AmazingPrime{
    fun run(){
        val n = readln().toInt()

        val time = measureTimedValue {
            for(i in 1..9){
                if(isPrime(i)) dfs(n, i)
            }
        }
        println(time.duration)
    }
    fun dfs(limit: Int, cur: Int){
        if (cur.toString().length == limit) {
            println(cur)
            return
        }

        for(i in 1..9){
            val next = cur * 10 + i
            if (isPrime(next)) dfs(limit, next)
        }
    }
    fun isPrime(n: Int): Boolean{
        if (n == 1) return false
        else if (n == 2) return true
        for(i in 2..sqrt(n.toDouble()).toInt()){
            if (n % i == 0) {
                return false
            }
        }
        return true
    }
}