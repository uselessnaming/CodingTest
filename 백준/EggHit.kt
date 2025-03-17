package 백준

// 백준 16987번 계란으로 계란치기

class EggHit {
    val eggs = mutableListOf<List<Int>>()
    var result = 0
    var N = 0
    fun run (){

        N = readln().toInt()
        repeat(N){ eggs.add(readln().split(" ").map{it.toInt()}) }

        dfs(0)

        println(result)
    }

    fun dfs(start: Int){
        if (start == N){
            val eggs = eggs.count{it[0] <= 0}
            if (eggs > result){ result = eggs }
            return
        }
        if (eggs[start][0] <= 0){
            dfs(start+1)
        } else {
            for(i in 0 until N){
                if (i != start){
                    val h = eggs[i][0]

                    val crashStart = eggs[start][0] - eggs[i][1]
                    val crashEnd = eggs[i][0] - eggs[start][1]
                    if (h > 0){
                        eggs[start] = listOf(crashStart, eggs[start][1])
                        eggs[i] = listOf(crashEnd, eggs[i][1])
                    }
                    dfs(start+1)

                    if (h > 0){
                        eggs[start] = listOf(crashStart + eggs[i][1], eggs[start][1])
                        eggs[i] = listOf(crashEnd + eggs[start][1], eggs[i][1])
                    }
                }
            }
        }
    }
}