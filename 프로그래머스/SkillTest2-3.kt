package 프로그래머스

class SkillTest2_3 {
    var answer = mutableListOf<Int>()
    lateinit var map: Array<String>
    lateinit var visited: Array<BooleanArray>

    val dir = listOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )

    // 대표적인 bfs 문제
    fun solution(maps: Array<String>): IntArray {
        map = maps
        visited = Array(maps.size){BooleanArray(maps[0].length){true} }

        // X가 아닌 곳에서 bfs를 시작 total 값을 answer에 추가
        for(i in maps.indices){
            for(j in maps[i].indices){
                if (visited[i][j] && maps[i][j] != 'X'){
                    visited[i][j] = false
                    bfs(i, j)
                }
            }
        }

        return if(answer.isNotEmpty()) answer.sorted().toIntArray() else intArrayOf(-1)
    }

    fun bfs(i: Int, j: Int){
        val queue = ArrayDeque<List<Int>>()
        var result = map[i][j].toString().toInt()
        queue.addFirst(listOf(i, j))

        while(queue.isNotEmpty()){
            val (x, y) = queue.removeFirst()
            for(i in 0 until 4){
                val xx = x + dir[i].first
                val yy = y + dir[i].second

                if (xx in 0 until map.size && yy in 0 until map[0].length){
                    if (visited[xx][yy] && map[xx][yy] != 'X'){
                        visited[xx][yy] = false
                        result += map[xx][yy].toString().toInt()
                        queue.addLast(listOf(xx, yy))
                    }
                }
            }
        }
        answer.add(result)

    }
}