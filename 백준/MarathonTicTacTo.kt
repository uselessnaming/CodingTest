package 백준

// 백준 3024번 마라톤 틱택토

class MarathonTicTacTo {
    val map = mutableListOf<String>()
    var answer = ""

    val dir = listOf(
        1 to 0,
        -1 to 1,
        1 to 1,
        0 to 1
    )

    fun run(){
        val n = readln().toInt()
        repeat(n){ map.add(readln()) }

        for(i in map.indices){
            for(j in map[0].indices){
                if (map[i][j] == '.') continue
                dfs(i, j, 0, map[i][j], 1)
                if (answer.isNotEmpty()){
                    println(answer)
                    return
                }
            }
        }

        println("ongoing")
    }

    fun dfs(i: Int, j: Int, befDir: Int, bef: Char, cnt: Int) {
        when(cnt){
            1 -> {
                for(idx in 0 until 4){
                    val (dx, dy) = dir[idx]
                    val xx = i + dx
                    val yy = j + dy

                    if (xx in 0 until map.size && yy in 0 until map.size){
                        if (map[xx][yy] == bef){
                            dfs(xx, yy, idx, map[xx][yy], cnt+1)
                        }
                    }
                }
            }
            2 -> {
                val (dx, dy) = dir[befDir]
                val xx = i + dx
                val yy = j + dy
                if (xx in 0 until map.size && yy in 0 until map.size){
                    if (map[xx][yy] == bef){
                        dfs(xx, yy, befDir, map[xx][yy], cnt+1)
                    }
                }
            }
            else -> {
                answer = map[i][j].toString()
                return
            }
        }
    }
}