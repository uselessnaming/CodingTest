package 프로그래머스

import kotlin.math.min

class MakeN{
    var result = 9
    var target = 0

    fun solution(N: Int, number: Int): Int{
        target = number
        logic(N, 0, 0)

        if(result == 9) return -1

        return result
    }

    fun logic(n: Int, cnt: Int, current: Int){
        if (cnt > 8) return

        if (current == target) {
            result = min(result,cnt)
            return
        }

        var nextNum = n

        for(i in 0 until 8 - cnt){
            logic(n, cnt + 1 + i, current + nextNum)
            logic(n, cnt + 1 + i, current - nextNum)
            logic(n, cnt + 1 + i, current / nextNum)
            logic(n, cnt + 1 + i, current * nextNum)
            nextNum += n * Math.pow(10.0, i+1.toDouble()).toInt()
        }
    }
}