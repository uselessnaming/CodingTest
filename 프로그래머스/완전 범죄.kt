package 프로그래머스

class PerfectCrime {
    val dp = Array<BooleanArray>(120+12){BooleanArray(120+12){false} }

    fun solution(info: Array<IntArray>, n: Int, m: Int): Int{
        val itemCnt = info.size
        dp[0][0] = true

        for (i in 0 until itemCnt){
            val nextDp = Array(120+12){BooleanArray(120+12){false} }
            val traceA = info[i][0]
            val traceB = info[i][1]

            for(a in 0 until n){
                for(b in 0 until m){
                    if(!dp[a][b]) continue

                    if(a + traceA < n){
                        nextDp[a + traceA][b] = true
                    }
                    if(b + traceB < m){
                        nextDp[a][b + traceB] = true
                    }
                }
            }
            for(a in 0 until n){
                for(b in 0 until m){
                    dp[a][b] = nextDp[a][b]
                }
            }
        }

        for(a in 0 until n){
            for(b in 0 until m){
                if(dp[a][b]) return a
            }
        }

        return -1
    }
}