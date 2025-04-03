package 백준

class MoveUnit{
    lateinit var map: MutableList<MutableList<Int>>
    lateinit var visited: Array<BooleanArray>
    lateinit var unit: Unit
    val dir = listOf(-1 to 0, 1 to 0, 0 to 1, 0 to -1)

    fun run(){
        // 입력
        val (n, m, a, b, k) = readln().split(" ").map{it.toInt()}
        map = MutableList(n+1){ MutableList(m+1){0} }
        visited = Array(n+1){ BooleanArray(m+1){false} }
        unit = Unit(a, b)
        repeat(k){
            val (x, y) = readln().split(" ").map{it.toInt()}
            map[x][y] = -1
        }
        val (sx, sy) = readln().split(" ").map{it.toInt()}
        val start = Point(sx,sy)
        val (ex, ey) = readln().split(" ").map{it.toInt()}
        val end = Point(ex, ey)

        val queue = ArrayDeque<Pair<Point, Int>>()
        queue.add(Pair(start, 0))
        visited[start.x][start.y] = true
        while(queue.isNotEmpty()){
            val (cur, cnt) = queue.removeFirst()
            println("cur $cur, cnt: $cnt")
            println("queue: $queue")
            if (cur.compareTo(end)){
                println(cnt)
                return
            }

            for(i in 0 until 4){
                val x = cur.x + dir[i].first
                val y = cur.y + dir[i].second
                println("next : $x $y")
                if (x in 1 .. n-a+1 && y in 1 ..m-b+1){
                    if (!visited[x][y]){
                        if(check(x, y, a, b, i)){
                            visited[x][y] = true
                            queue.add(Pair(Point(x, y), cnt+1))
                        }
                    }
                }
            }
        }

        // bfs
        // 가면서 좌우로 이동할 경우 a만큼 의 범위 내에 장애물이 있는지 확인해야 함
        // 상하로 이동할 경우 b만큼의 범위 내에 장애물이 있는지 확인해야 함
        // 가장 왼쪽 위치가 해당 위치에 도착하도록만 하면 정답일듯?
        println(-1)
    }
    fun check(x: Int, y: Int, a: Int, b: Int, idx: Int): Boolean{
        println("check call : $x $y $a $b $idx")
        return when(idx){
            0 -> {
                for(i in 0 until b){ if (map[x][y+i] == -1) return false }
                true
            }
            1 -> {
                for(i in 0 until b){if (map[x+a-1][y+i] == -1) return false }
                true
            }
            2 -> {
                for(i in 0 until a){if (map[x+i][y+b-1] == -1) return false}
                true
            }
            else -> {
                for(i in 0 until a){ if (map[x+i][y] == -1) return false }
                true
            }
        }
    }
}

data class Unit(
    val xSize: Int,
    val ySize: Int
)

data class Point(
    val x: Int,
    val y: Int
){
    fun compareTo(a: Point): Boolean{
        return this.x == a.x && this.y == a.y
    }
}