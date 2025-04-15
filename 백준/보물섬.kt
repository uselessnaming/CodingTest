package 백준

import java.util.ArrayDeque
import kotlin.math.max

// 백준 2589번 보물섬
// 그냥 어렵게 생각하지 말고 모든 L 위치에서 bfs를 시행하고
// 그 상황에서 최대값을 받아내면 됨
// 아래에 있는 로직이 왜 틀렸습니다를 받았을까?

class TreasureIsland{
    lateinit var visited: Array<IntArray>
    val map = mutableListOf<String>()

    val dir = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
    var ans = -1

    fun run(){
        // 입력
        val (r, c) = readln().split(" ").map{it.toInt()}
        repeat(r){ map.add(readln().trim()) }
        visited = Array(r){ IntArray(c){ 0 } }

        // 알고리즘
        for(i in 0 until r){
            for(j in 0 until c){
                if (map[i][j] == 'L'){
                    bfs(i, j)
                }
            }
        }

        // 출력
        println(ans)
    }

    fun bfs(x: Int, y: Int) {
        val q = ArrayDeque<Node>()
        val visited = Array(map.size){ BooleanArray(map[0].length){false} }
        q.add(Node(x, y, 0))
        visited[x][y] = true

        while(q.isNotEmpty()){
            val (xx, yy, cnt) = q.removeFirst()
            ans = max(cnt, ans)
            for(i in dir.indices){
                val nx = xx + dir[i].first
                val ny = yy + dir[i].second
                if (nx in 0 until map.size && ny in 0 until map[0].length){
                    if (!visited[nx][ny] && map[nx][ny] == 'L'){
                        q.addLast(Node(nx, ny, cnt+1))
                        visited[nx][ny] = true
                    }
                }
            }
        }
    }
}

data class Node(
    val r: Int,
    val c: Int,
    val dis: Int
)
//fun bfs(i: Int, j: Int){
//    // 임의의 점에서 가장 먼 거리에 있는 점을 찾음
//    // 해당 점에서 출발해서 가장 먼 거리에 있는 점까지의 거리를 max 값으로 설정
//    val queue = ArrayDeque <Pair<Point, Int>>()
//    queue.add(Point(i, j) to 0)
//    var firstPoint = Point(0,0)
//
//    while(queue.isNotEmpty()){
//        val (start, cnt) = queue.removeFirst()
//
//        for(i in 0 until 4){
//            val x = start.x + dir[i].first
//            val y = start.y + dir[i].second
//
//            if (x in 0 until map.size && y in 0 until map[0].length){
//                if (map[x][y] == 'L' && visited[x][y] == 0){
//                    visited[x][y] = cnt+1
//
//                    queue.add(Point(x,y) to cnt+1)
//                    firstPoint = Point(x,y)
//                }
//            }
//        }
//    }
//
//    queue.clear()
//    queue.add(firstPoint to 1)
//    visited = Array(map.size){ IntArray(map[0].length){ 0 } }
//    visited[firstPoint.x][firstPoint.y] = 1
//
//    while(queue.isNotEmpty()){
//        val (start, cnt) = queue.removeFirst()
//        for(i in 0 until 4){
//            val x = start.x + dir[i].first
//            val y = start.y + dir[i].second
//
//            if (x in 0 until map.size && y in 0 until map[0].length){
//                if (map[x][y] == 'L' && visited[x][y] == 0){
//                    visited[x][y] = cnt+1
//
//                    queue.add(Point(x,y) to cnt+1)
//                }
//            }
//        }
//    }
//    println(visited.map{it.toList()})
//
//    ans = max(ans, visited.map{it.max()}.max())
//}