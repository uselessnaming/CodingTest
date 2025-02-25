package 프로그래머스

import kotlin.math.abs

const val maxN = 300000
class MakeAllZero {
    var adj = Array(maxN){mutableListOf<Int>()}
    val b = LongArray(maxN)
    var ans = 0L

    fun solution(a: IntArray, edges: Array<IntArray>): Long{
        for(i in a.indices) b[i] = a[i].toLong()
        for((u, v) in edges){
            adj[u].add(v)
            adj[v].add(u)
        }
        if (dfs(-1, 0) != 0L) ans = -1
        return ans
    }

    fun dfs(par: Int, n: Int): Long{
        var ret = b[n]
        for (i in 0..adj[n].size-1){
            if(adj[n][i]!=par) ret+=dfs(n, adj[n][i])
        }
        ans+=abs(ret)
        return ret
    }
}