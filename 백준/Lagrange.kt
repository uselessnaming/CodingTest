package 백준

import kotlin.math.sqrt

// 백준 3933번 라그랑주의 네 제곱수 정리
// 아직 pass..
// 미완

class Lagrange {
    var n = 0
    var maxN = 0
    var answer = 0
    fun run (){
        while(true){
            n = readln().toInt()
            if (n == 0) break

            maxN = sqrt(n.toDouble()).toInt()
            for(i in 1..maxN){
                dfs(i, 1)
            }

            println(answer)
        }
    }

    fun dfs(cur: Int, cnt: Int){
        if (cur > n) {
            return
        } else if (cur == n) {
            answer++
            return
        }
        if (cnt >= 4){ return }

        for(i in 1 .. maxN){
            dfs(cur + i*i, cnt+1)
        }
    }
}