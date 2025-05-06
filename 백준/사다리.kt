package 백준

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.min
import kotlin.math.sqrt

// 2022번 사다리
// 아 소수 처리하는 거 답없다
// 알고리즘은 맞는데..

class Ladder{
    fun run(){
        val (x, y, c) = readln().split(" ").map{it.toDouble()}

        var left = 0.toDouble()
        var right = min(x, y)

        while(left <= right){
            val mid = (left+right) / 2.0
            val res1 = getX1(mid, y, c)
            val res2 = getX2(mid, x, c)
            if (res1 == res2){
                break
            } else if (res1 > res2){
                right = mid-0.001
            } else {
                left = mid+0.001
            }
        }
        println(BigDecimal(left).setScale(3, RoundingMode.HALF_UP).toDouble())
    }

    fun getX1(mid: Double, y: Double, c: Double): Double{
        val res = c / (sqrt(y*y - mid*mid) / mid)
        return BigDecimal(res).setScale(3, RoundingMode.HALF_UP).toDouble()
    }
    fun getX2(mid: Double, x: Double, c: Double): Double{
        val res = -(c - sqrt(x*x - mid*mid)) / (sqrt(x*x - mid*mid) / mid)
        println("x2 function : $res")
        return BigDecimal(res).setScale(3, RoundingMode.HALF_UP).toDouble()
    }
}