package 백준

import kotlin.math.*

// 2417번 백준
// 2진 탐색은 맞지만 sqrt 함수를 사용할 경우 소수점 계산에서 오차 발생으로 인해 정확하지 않은 답이 나옴
// 따라서 double형에서 pow를 이용하여 계산해야 함
class IntSquare{
    fun run(){
        val n = readln().toLong()

        var low = 0L
        var high = Long.MAX_VALUE

        var ans = Long.MAX_VALUE

        while(low <= high){
            val mid = low + (high - low) / 2

            if (mid.toDouble().pow(2.toDouble()) >= n){
                ans = min(ans, mid)
                high = mid - 1
            } else {
                low = mid + 1
            }
        }

        println(ans)
    }
}