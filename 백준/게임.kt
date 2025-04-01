package 백준

import kotlin.math.max
import kotlin.math.min

// 백준 1584번 게임
// 알고리즘 1
// input을 받음
// 위험 구역은 1로
// 죽음의 구역은 -1으로
// 안전 구역은 0으로
// dfs로 실행
// 안전 구역을 먼저 돌리고 그 후에 위험 구역으로 돌리기
// 도착하면 answer을 정하고 return
// 2가지 key point
// 1 >> 해당 문제는 dfs로 할 경우 500*500번의 경로를 계속 탐색하므로 메모리 초과가 발생할 수 있음
// 2 >> 0인 안전 구역을 우선적으로 가야하므로 다른bfs와 다르게 0일 경우에는 queue의 앞 부분에 넣어줌

class Game{
    val visited = Array(501){BooleanArray(501){false} }
    val map = Array(501){ IntArray(501){0} }
    val dir = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
    var answer = Int.MAX_VALUE
    fun run(){
        // 위험 구역 받기
        val dangerousArea = readln().toInt()
        repeat(dangerousArea){
            val(sx, sy, ex, ey) = readln().split(" ").map{it.toInt()}
            val startX = min(sx, ex)
            val endX = max(sx, ex)
            val startY = min(sy, ey)
            val endY = max(sy,ey)
            for(x in startX..endX){
                for(y in startY..endY){
                    map[x][y] = 1
                }
            }
        }
        // 죽음 구역 입력 받기
        val deathArea = readln().toInt()
        repeat(deathArea){
            val (sx, sy, ex, ey) = readln().split(" ").map{it.toInt()}
            val startX = min(sx, ex)
            val endX = max(sx, ex)
            val startY = min(sy, ey)
            val endY = max(sy,ey)
            for(x in startX..endX){
                for(y in startY..endY){
                    map[x][y] = -1
                }
            }
        }

        bfs(0, 0)

        if (answer == Int.MAX_VALUE) println(-1)
        else println(answer)
    }

    fun bfs(startX: Int, startY: Int){
        visited[startX][startY] = true
        val queue = ArrayDeque<List<Int>>()
        queue.add(listOf(startX, startY, 0))
        while(queue.isNotEmpty()){
            val (x, y, cnt) = queue.removeFirst()
            if (x == 500 && y == 500) {
                answer = min(answer, cnt)
            }

            for(idx in 0 until 4){
                val xx = x + dir[idx].first
                val yy = y + dir[idx].second

                if (xx in 0 until 501 && yy in 0 until 501){
                    if (!visited[xx][yy]){
                        if (map[xx][yy] == 0){
                            visited[xx][yy] = true
                            queue.addFirst(listOf(xx, yy, cnt))
                        }
                        else if (map[xx][yy] == 1){
                            visited[xx][yy] = true
                            queue.addLast(listOf(xx, yy, cnt+1))
                        }
                    }
                }
            }
        }
    }
}
