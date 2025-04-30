package 백준

import java.lang.Integer.min
import kotlin.math.min
import kotlin.system.measureTimeMillis

// 1300번 K번째 수

class KNumber{
    val arr = mutableMapOf<Long, Int>()
    fun run(){
        val n = readln().toLong()
        val k = readln().toLong()

        var left = 1L
        var right = n*n
        while(left <= right){
            val mid = (left + right) / 2
            if (checkIdx(n, mid) >= k){
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        println(left)
    }

    fun checkIdx(n: Long, value: Long): Long{
        var sum = 0L
        for(i in 1..n){
            var rem = value / i
            if (rem >= n){rem = n}
            sum += rem
        }
        return sum
    }
}