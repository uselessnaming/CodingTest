package 백준

import java.util.LinkedList
import kotlin.math.min

// 해설을 보면서 했는데도 3%에서 메모리 초과가 발생함
// 일단 일반적인 bfs, dfs 방식으로는 불가능함
// 몇 번 뛰었는지에 대한 정보를 저장해야 했기에 visited가 3차원으로 저장됨
// dp와 dfs 혹은 bfs와 연계해야 하는 느낌

class MonkeyWantToHorse{
    val map = mutableListOf<List<Int>>()

    val horseDir = listOf(
        -2 to 1,
        -2 to -1,
        2 to 1,
        2 to -1,
        1 to 2,
        -1 to 2,
        1 to -2,
        -1 to -2
    )
    val monkeyDir = listOf(
        0 to 1,
        0 to -1,
        1 to 0,
        -1 to 0
    )

    fun run (){
        // 입력
        val k = readln().toInt()
        val (w, h) = readln().split(" ").map{it.toInt()}
        repeat(h){ map.add(readln().split(" ").map{it.toInt()}) }
        val visited = Array(h) { Array(w){ Array(k+1){false} } }
        val queue = LinkedList<List<Int>>()

        // 원숭이의 움직임은 우, 상, 우대각, 상대각 총 4가지로 제한
        queue.add(listOf(0, 0, 0, 0))
        visited[0][0][0] = true

        // bfs로 푸는 것이 더 현명해
        // 모두 다 해봐야 하거든
        while(queue.isNotEmpty()){
            val (x, y, curK, cnt) = queue.removeFirst()

            if (x == h-1 && y == w-1){
                println(cnt)
                return
            }

            for (i in 0 until 4){
                val xx = x + monkeyDir[i].first
                val yy = y + monkeyDir[i].second

                if (xx !in 0 until h || yy !in 0 until w || visited[xx][yy][curK] || map[xx][yy] == 1) continue
                queue.addLast(listOf(xx, yy, curK, cnt+1))
                visited[xx][yy][curK] = true
            }

            if (curK < k){
                for (i in 0 until 8){
                    val xx = x + horseDir[i].first
                    val yy = y + horseDir[i].second

                    if(xx !in 0 until h || yy !in 0 until w || visited[xx][yy][curK] || map[xx][yy] == 1) continue
                    visited[xx][yy][curK+1] = true
                    queue.add(listOf(xx, yy, curK+1, cnt+1))
                }
            }
        }

        println(-1)
    }
}