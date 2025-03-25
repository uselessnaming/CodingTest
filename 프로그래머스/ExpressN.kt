package 프로그래머스

import java.lang.Math.pow
import kotlin.math.min

class ExpressN {
    // answer은 최대값인 9로 시작
    var answer = 9
    var target = 0
    // dfs 방법으로 해결
    // 각각의 수에 따라 * - + / 각각 실행
    // 자릿수를 더해주어 다음 연산 시행

    fun solution(N: Int, number: Int): Int{
        target = number

        dfs(N, 0, 0)

        return if(answer > 8) -1 else answer
    }

    fun dfs(n: Int, cnt: Int, current: Int){
        if (cnt > 8) return

        if (current == target){
            answer = min(answer, cnt)
            return
        }

        var nextN = n
        for(i in 0 until 8-cnt){
            dfs(n, cnt+1+i, current + nextN)
            dfs(n, cnt+1+i, current - nextN)
            dfs(n, cnt+1+i, current / nextN)
            dfs(n, cnt+1+i, current * nextN)
            nextN += n * pow(10.0, (i+1).toDouble()).toInt()
        }
    }
}