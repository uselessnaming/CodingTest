package 백준

import kotlin.math.max

// 2468번 백준 안전영역

class SafeArea {
    val area = mutableListOf<List<Int>>()
    var areaCnt = 0
    var limit = 0
    val dir = listOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )

    lateinit var visited: Array<BooleanArray>

    fun run(){
        var ans = 0
        val n = readln().toInt()
        repeat(n){
            val input = readln().split(" ").map{it.toInt()}
            area.add(input)
            limit = max(limit, input.max())
        }

        for(height in 0 until limit){
            visited = Array(n){BooleanArray(n){false} }
            areaCnt = 0
            for(i in 0 until n){
                for(j in 0 until n){
                    if (!visited[i][j] && area[i][j] > height){
                        visited[i][j] = true
                        dfs(i, j, height)
                        areaCnt++
                    }
                }
            }

            ans = max(ans, areaCnt)
        }

        println(ans)
    }

    fun dfs(x: Int, y: Int, height: Int){
        for(i in 0 until 4){
            val xx = x + dir[i].first
            val yy = y + dir[i].second

            if (xx in 0 until area.size && yy in 0 until area[0].size){
                if (!visited[xx][yy] && area[xx][yy] > height){
                    visited[xx][yy] = true
                    dfs(xx, yy, height)
                }
            }
        }
    }
}