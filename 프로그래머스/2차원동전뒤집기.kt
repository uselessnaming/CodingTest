package 프로그래머스

import kotlin.math.min

class CoinFlip {
    var n = 0
    var m = 0
    var answer = Int.MAX_VALUE
    lateinit var t: Array<IntArray>
    lateinit var b: Array<IntArray>

    fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int{
        n = beginning.size
        m = beginning[0].size
        t = target
        b = beginning

        dfs(0, 0)

        return if(answer == Int.MAX_VALUE) -1 else answer
    }

    fun flipRow(r: Int){
        for(j in 0 until m){
            b[r][j] = b[r][j] xor 1
        }
    }

    fun compareColumn(c: Int): Int{
        var cnt = 0
        for (i in 0 until n){
            if (b[i][c] == t[i][c]) cnt++
        }
        return when(cnt){
            0 -> 1
            n -> 0
            else -> -1
        }
    }

    fun dfs(r: Int, cnt: Int){
        if (r == n){
            var flag = true
            var flips = cnt
            for (j in 0 until m){
                val ret = compareColumn(j)
                if (ret == -1){
                    flag = false
                    break
                }
                flips += ret
            }
            if (flag) answer = min(answer, flips)
            return
        }

        dfs(r+1, cnt)

        flipRow(r)
        dfs(r+1, cnt+1)

        flipRow(r)
    }
}