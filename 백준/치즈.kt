package 백준

// 백준 2636번 치즈
// 생각의 전환!
// 치즈로 bfs를 시작하지 않고 공기로 시작해서 녹이는 것을 생각해야 풀 수 있음!!

class Cheese{
    lateinit var map: Array<IntArray>
    lateinit var visited: Array<BooleanArray>
    val dir = listOf(-1 to 0, 1 to 0, 0 to 1, 0 to -1)

    val points = mutableListOf<Point>()
    var cnt = 0
    var day = 0

    fun run(){
        // 입력
        val (n, m) = readln().split(" ").map{ it.toInt() }
        map = Array(n){ IntArray(m){0} }
        repeat(n){ map[it] = readln().split(" ").map{it.toInt()}.toIntArray() }

        // 치즈가 맞닿아 있는 곳을 확인하고 points에 추가
        // points를 반복하며 사방을 확인하여 1을 0으로 변경, 변경하는 개수만큼 cnt 갱신
        // 반복
        while(true){
            visited = Array(n){ BooleanArray(m){false} }
            findAirOnCheese()
            if (points.isEmpty()) {
                println(day)
                println(cnt)
                break
            }
            meltingCheese()

            day++
        }
    }

    private fun findAirOnCheese(){
        val queue = ArrayDeque<Point>()
        queue.add(Point(0,0))
        visited[0][0] = true
        while(queue.isNotEmpty()){
            val cur = queue.removeFirst()

            for(idx in 0 until 4){
                val x = cur.x + dir[idx].first
                val y = cur.y + dir[idx].second

                if (x in 0 until map.size && y in 0 until map[0].size){
                    if (!visited[x][y]){
                        visited[x][y] = true
                        if (map[x][y] == 0){
                            queue.add(Point(x, y))
                        } else if (map[x][y] == 1){
                            points.add(Point(x,y))
                        }
                    }
                }
            }
        }
    }

    private fun meltingCheese(){
        cnt = 0
        points.forEach{ point ->
            map[point.x][point.y] = 0
            cnt++
        }
        points.clear()
    }
}